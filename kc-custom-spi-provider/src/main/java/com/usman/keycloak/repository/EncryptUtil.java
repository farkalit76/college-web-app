package com.usman.keycloak.repository;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtil {

    public static String hashPassword(String plainPassword) {
        // Generate salt automatically and hash
    	//salt value could be 2^(8/10/12/14/16).
        // The salt number is increases the time of hash creation is increased
        //so keep 10 or 12 only.
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
    	long start = System.currentTimeMillis();
        boolean checked = BCrypt.checkpw(plainPassword, hashedPassword);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("****Time taken for pass validation per hash (ms): " + elapsed);
        return checked;
    }

}