# The release test APK is also minified. JUnit discovers these methods by reflection.
-keep class it.skrape.skrapeitexample.PublishedLibraryTest { *; }
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
