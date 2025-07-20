package com.example.auth_service.utill;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    private static Dotenv dotenv = Dotenv.configure().load();

    public static String getSecret(String secret) {
        return dotenv.get(secret);
    }

    public static long getExpirationMs(String expirationMs) {
        String value = dotenv.get(expirationMs);
        return Long.parseLong(value);
    }
}
