# Commons Logging resolves its API and backend by class name.
-keep class org.apache.commons.logging.* { *; }
-keep class org.apache.commons.logging.impl.LogFactoryImpl { *; }
-keep class org.apache.commons.logging.impl.Jdk14Logger { public <init>(java.lang.String); }
-keep class org.apache.commons.logging.impl.SimpleLog { public <init>(java.lang.String); }
-keep class org.apache.commons.logging.impl.Log4JLogger { public <init>(java.lang.String); }

# Optional desktop/server integrations are unavailable on Android.
-dontwarn javax.naming.InvalidNameException
-dontwarn javax.naming.NamingException
-dontwarn javax.naming.directory.Attribute
-dontwarn javax.naming.directory.Attributes
-dontwarn javax.naming.ldap.LdapName
-dontwarn javax.naming.ldap.Rdn
-dontwarn javax.servlet.ServletContainerInitializer
-dontwarn org.ietf.jgss.GSSContext
-dontwarn org.ietf.jgss.GSSCredential
-dontwarn org.ietf.jgss.GSSException
-dontwarn org.ietf.jgss.GSSManager
-dontwarn org.ietf.jgss.GSSName
-dontwarn org.ietf.jgss.Oid
