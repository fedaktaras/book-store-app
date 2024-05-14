package com.example.bookstoreapp.security;

import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private static final String SEPARATOR = ":";

    public String generateToken(String username) {
        String rawToken = username + SEPARATOR + System.currentTimeMillis() + SEPARATOR + secret;
        return toHexString(rawToken.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        String rawToken = fromHexString(token);
        String[] parts = rawToken.split(SEPARATOR);
        return parts[0];
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        String rawToken = fromHexString(token);
        String[] parts = rawToken.split(SEPARATOR);
        long timestamp = Long.parseLong(parts[1]);
        return (System.currentTimeMillis() - timestamp) > expiration;
    }

    private String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String fromHexString(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
