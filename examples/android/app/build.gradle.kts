plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "it.skrape.skrapeitexample"
    compileSdk = 37

    defaultConfig {
        applicationId = "it.skrape.skrapeitexample"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["class"] = "it.skrape.skrapeitexample.PublishedLibraryTest"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            // This is an executable consumer example, not an app distributed to users.
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            testProguardFiles("instrumentation-rules.pro")
        }
    }
    testBuildType = "release"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures { compose = true }
    packaging.resources.excludes += setOf("META-INF/DEPENDENCIES", "META-INF/INDEX.LIST")
    // HtmlUnit embeds the same Apache HTTP public-suffix resource as the HTTP fetchers.
    packaging.resources.pickFirsts += "mozilla/public-suffix-list.txt"
}

kotlin { jvmToolchain(25) }

dependencies {
    implementation(platform(libs.compose.bom))
    // Keep Coil's client and the instrumentation fixture server on the same OkHttp API.
    implementation(platform(libs.okhttp.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.coil.compose)
    implementation("it.skrape:skrapeit:${providers.gradleProperty("skrapeitVersion").getOrElse("0-SNAPSHOT")}")

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.mockwebserver)
}
