# Instrumentation shares these dependencies with the optimized target APK.
# Retain their API for the runner and fixture server, compiled in a separate APK.
# The skrape.it implementation itself remains eligible for R8 optimization.
-keep class androidx.tracing.Trace { *; }
-keep class kotlin.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
