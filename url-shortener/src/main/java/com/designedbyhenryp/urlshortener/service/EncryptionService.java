package com.designedbyhenryp.urlshortener.service;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import static com.designedbyhenryp.urlshortener.constants.UrlShortenerConstants.*;

@Service
public class EncryptionService {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    public String generateString() {
        byte[] array = new byte[UPPER_BOUNDARY];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    public static void setKey(final String myKey) {
        MessageDigest sha;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance(SHA);
            key = sha.digest(key);
            key = Arrays.copyOf(key, KEY_UPPER_BOUNDARY);
            secretKey = new SecretKeySpec(key, AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance(CIPHER_METHOD);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.getMessage());
        }
        return null;
    }

    public static String decrypt(final String strToDecrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
