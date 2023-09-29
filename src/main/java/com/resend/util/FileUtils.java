package com.resend.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileUtils {

    public static String encodeFileToBase64(String filename) throws IOException {
        return Base64.getEncoder().encodeToString(loadResourceFile(filename));
    }

    private static byte[] loadResourceFile(String filename) throws IOException {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + filename);
            }
            return inputStream.readAllBytes();
        }
    }
}
