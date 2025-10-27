package org.usman.api.college.common.utility;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 08-10-2024
 * @since 1.0
 */
@Slf4j
public class EncodeValue {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String codeVerifier = generateCodeVerifier();
        log.info("EncodeValue codeVerifier:{}", codeVerifier);
        String codeChallenge = generateCodeChallenge(codeVerifier);
        log.info("EncodeValue codeChallenge:{}", codeChallenge);
    }

    public static String generateCodeVerifier() throws UnsupportedEncodingException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
    }

    public static String generateCodeChallenge(String codeVerifier) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytes = codeVerifier.getBytes("US-ASCII");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }

}
