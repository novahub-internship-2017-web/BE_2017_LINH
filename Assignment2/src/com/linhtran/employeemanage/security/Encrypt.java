package com.linhtran.employeemanage.security;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;


public class Encrypt {
    private Encrypt() {

    }

    public static String encryptPass(String password) {
        String passwordHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return passwordHash;
    }

    public static boolean verifyPassword(String password, String passwordHash) {
        String hash;
        try {
            hash = encryptPass(password);
            if (hash != null) {
                return hash.equals(passwordHash);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
