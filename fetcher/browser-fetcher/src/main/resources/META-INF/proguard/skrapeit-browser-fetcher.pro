# HtmlUnit builds its JavaScript bindings and browser feature table by reflection.
-keep enum com.gargoylesoftware.htmlunit.BrowserVersionFeatures { *; }
-keepclassmembers class com.gargoylesoftware.htmlunit.** {
    public static final java.lang.String TAG_NAME;
}
-keepnames class com.gargoylesoftware.htmlunit.javascript.host.**
-keepclassmembers class com.gargoylesoftware.htmlunit.javascript.host.** {
    public <init>();
    @com.gargoylesoftware.htmlunit.javascript.configuration.Jsx* <methods>;
    @com.gargoylesoftware.htmlunit.javascript.configuration.Jsx* <fields>;
}
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
# Custom JavaScript functions are registered through arrays of method names.
-keepclassmembers class com.gargoylesoftware.htmlunit.javascript.host.**Custom {
    public <methods>;
}
# Rhino resolves its argument types by their fully qualified names.
-keepnames class net.sourceforge.htmlunit.corejs.javascript.**
-keep class net.sourceforge.htmlunit.corejs.javascript.jdk18.VMBridge_jdk18 {
    public <init>();
}
-keep class net.sourceforge.htmlunit.corejs.javascript.Interpreter {
    public <init>();
}
-keep class net.sourceforge.htmlunit.corejs.javascript.regexp.RegExpImpl {
    public <init>();
}
-keep class net.sourceforge.htmlunit.corejs.javascript.regexp.NativeRegExp {
    public static void init(...);
}

# Optional desktop integrations and compile-time annotations are unavailable on Android.
-dontwarn edu.umd.cs.findbugs.annotations.SuppressFBWarnings
-dontwarn java.awt.font.FontRenderContext
-dontwarn java.awt.font.LineBreakMeasurer
-dontwarn java.awt.font.TextLayout
-dontwarn java.awt.geom.AffineTransform
-dontwarn java.beans.BeanDescriptor
-dontwarn java.beans.BeanInfo
-dontwarn java.beans.IntrospectionException
-dontwarn java.beans.Introspector
-dontwarn java.beans.PropertyDescriptor
-dontwarn javax.imageio.ImageIO
-dontwarn javax.imageio.ImageReader
-dontwarn javax.imageio.stream.ImageInputStream
-dontwarn javax.lang.model.SourceVersion

# Applets/AWT canvas are desktop-only; Jetty WebSockets are excluded on Android (see #93).
-dontwarn java.applet.Applet
-dontwarn java.applet.AppletContext
-dontwarn java.applet.AppletStub
-dontwarn java.awt.Color
-dontwarn org.eclipse.jetty.client.HttpClient
-dontwarn org.eclipse.jetty.io.ByteBufferPool
-dontwarn org.eclipse.jetty.util.ssl.SslContextFactory
-dontwarn org.eclipse.jetty.websocket.api.RemoteEndpoint
-dontwarn org.eclipse.jetty.websocket.api.Session
-dontwarn org.eclipse.jetty.websocket.api.WebSocketAdapter
-dontwarn org.eclipse.jetty.websocket.api.WebSocketPolicy
-dontwarn org.eclipse.jetty.websocket.client.WebSocketClient
