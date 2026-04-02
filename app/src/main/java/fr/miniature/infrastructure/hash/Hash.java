package fr.miniature.infrastructure.hash;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import fr.miniature.domain.models.hash.HashInterface;

import java.security.MessageDigest;

public final class Hash implements HashInterface {
    private static HashInterface instance;

    public static HashInterface getInstance(){        
        if(instance == null){
            instance = new Hash();
        }

        return instance;
    }

    public String hash(String text) {
    
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
            text.getBytes(StandardCharsets.UTF_8));
            return new String(encodedhash, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
       
    }
}
