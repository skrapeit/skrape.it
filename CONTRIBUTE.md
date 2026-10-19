# How-To

### run all tests

```shell
./gradlew clean check
```

Use JDK 25 to run Gradle. Kotlin 2.4.21 compiles with a JDK 25 toolchain and targets Java 17
bytecode. Java `--release=17` and Kotlin `-Xjdk-release=17` also prevent accidental use of newer
JDK APIs. CI runs the tests on both JDK 17 and 25 on Ubuntu and Windows. To select the test JVM
locally, run `./gradlew check -PtestJavaVersion=17` (the default is 25).

Detekt uses 2.0.0-alpha.6 because the 1.x series does not support the new Kotlin toolchain.

The full Ubuntu CI test run enforces at least 80% line coverage. Windows CI still builds, tests
and generates documentation, but reports coverage violations as warnings because Docker-backed
tests are disabled on Windows. To use the same coverage behavior locally, pass
`-Pkover.verify.warn=true` to Gradle.

### generate API documentation

```shell
./gradlew :dokkaGeneratePublicationHtml dokkaJavadocJar
```

Dokka 2 generates Kotlin HTML documentation. The root documentation at `build/dokka/html/index.html`
combines all published modules. Maven publications include sources and this HTML documentation
in their `-sources.jar` and `-javadoc.jar` artifacts, respectively.

Fetcher JARs also include Android consumer rules under `META-INF/proguard/`. These preserve
the third-party APIs loaded by reflection and identify optional desktop/server integrations.
Common rules live in `base-fetcher`; `browser-fetcher` adds the HtmlUnit-specific rules.

`AsyncFetcher` owns the HTTP transport, request conversion and response conversion.
`HttpFetcher` adapts it with `runBlocking`, so both entry points share the same behavior.

### local static analysis with qodana

```shell
docker run --rm -it -v $(pwd)/:/data/project/ -v $(pwd)/qodana/results:/data/results/ -p 8080:8080 jetbrains/qodana-jvm:latest --show-report
```

## Maintenance
### verify Android compatibility

The [Android consumer](examples/android/README.md) resolves locally published Maven artifacts,
builds a minified release with AGP 9.4 and built-in Kotlin, and tests parsing, HTTP, coroutines
and JavaScript rendering on an API 36 emulator. These checks run in CI before snapshot publishing.

### check if all dependencies are up-to-date

```shell
./gradlew dependencyUpdates
```

### auto bump all dependencies to latest stable versions and build

```shell
./gradlew versionCatalogUpdate
./gradlew build dokkaJavadocJar
```

## Release

Run the **Release** GitHub Actions workflow on `master`. Select `automatic` to derive the version
from conventional commits, or choose a release type. `stable` removes a pre-release suffix;
if the latest version is already stable, no release is created. The workflow builds and publishes
the selected commit, then tags that exact commit. It first requires successful CI for that commit,
including the JVM 17/25 and Android checks. It does not create a version-bump commit.

Local builds default to `0-SNAPSHOT`. Release versions are passed with `-PVERSION_NAME` rather
than written back into `gradle.properties`.

Publishing keeps using the existing GitHub repository secrets. The workflows map them to the
property names expected by the Maven Central publishing plugin:

| Existing repository secret | Gradle environment variable |
| --- | --- |
| `ORG_GRADLE_PROJECT_SONATYPEUSERNAME` | `ORG_GRADLE_PROJECT_mavenCentralUsername` |
| `ORG_GRADLE_PROJECT_SONATYPEPASSWORD` | `ORG_GRADLE_PROJECT_mavenCentralPassword` |
| `ORG_GRADLE_PROJECT_SIGNINGKEY` | `ORG_GRADLE_PROJECT_signingInMemoryKey` |
| `ORG_GRADLE_PROJECT_SIGNINGKEYID` | `ORG_GRADLE_PROJECT_signingInMemoryKeyId` |
| `ORG_GRADLE_PROJECT_SIGNINGPASSWORD` | `ORG_GRADLE_PROJECT_signingInMemoryKeyPassword` |

The Sonatype username/password secrets must contain a **Central Portal user token**. Legacy
OSSRH tokens do not work with the Central Portal; replace their values in the same existing
secrets if necessary. The existing ASCII-armored private signing key and its password are reused;
the signing subkey ID is optional. Local publishing uses the Gradle environment variables in the
right column (or the corresponding properties in `~/.gradle/gradle.properties`).

The `it.skrape` namespace must be available in the Central Portal, with snapshots enabled.
Snapshots use `https://central.sonatype.com/repository/maven-snapshots/` and a version such as
`1.4.0-SNAPSHOT`, derived by incrementing the latest tag's minor version. The CI job summary
shows the version actually published. The consumer example uses a pinned, existing pre-release
and local HTML fixtures so its tests do not depend on a prior snapshot upload or external pages.

### publish to Maven Central:

```shell
./gradlew clean publishToMavenCentral -PVERSION_NAME=1.3.0 -PsignAllPublications=true
```

### publish a snapshot to Maven Central:

```shell
./gradlew clean publishToMavenCentral -PVERSION_NAME=1.3.1-SNAPSHOT -PsignAllPublications=true
```

### install project into local maven repository

```shell
./gradlew clean publishToMavenLocal
```

To inspect publications without modifying your normal Maven cache:

```shell
./gradlew publishToMavenLocal -Dmaven.repo.local="$PWD/build/maven-smoke-test"
```

### update gradle wrapper

```shell
./gradlew wrapper --gradle-version 9.7.1
./gradlew -p examples/android wrapper --gradle-version 9.7.1
```
