package net.vulkanmod;

public class Helper {
    public enum JavaExternalExposed {
        EXPOSED,
        NOT_EXPOSED,
        NOT_EXPOSED_DUE_SECURITY,
    }
    public static JavaExternalExposed isJavaExternalExposed() {
        try {
            // Try accessing a class or method from java.internal
            Class<?> clazz = Class.forName("jdk.internal.misc.Unsafe");

            return JavaExternalExposed.EXPOSED;
        } catch (ClassNotFoundException e) {
            // Class not found implies internal API is not accessible
            return JavaExternalExposed.NOT_EXPOSED;
        } catch (SecurityException e) {
            // Security manager may block access
            return JavaExternalExposed.NOT_EXPOSED_DUE_SECURITY;
        }
    }
}
