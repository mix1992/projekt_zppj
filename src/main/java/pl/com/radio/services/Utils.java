/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author bartek
 *
 */

/**
 * Class Utils provides applications that the message digest with algorithm SHA-256
 *
 */
public class Utils {


    public static String sha256(byte[] data) {
        return Utils.digest("SHA-256", data);
    }


    /**
     * @param digest is define using hash functions (SHA-256)
     * @param data the input to be updated before the digest is completed
     * @return the array of bytes for the resulting hash value
     */
    public static String digest(String digest, byte[] data) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(digest);
            return Hex.encodeHexString(md.digest(data));
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }
}
