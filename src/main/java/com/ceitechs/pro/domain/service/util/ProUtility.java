/**
 * 
 */
package com.ceitechs.pro.domain.service.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author vctrowino
 *
 */
public class ProUtility {
	
	/**
     * Used by JCE for D/Encryption
     * 
     * @return Cipher
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance("AES");
    }
	
	/**
	 * 
	 * @param encryptedText
	 * @param encodedKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedText, String encodedKey) throws Exception {
        SecretKey secretKey = secretKey(encodedKey);
        return decrypt(encryptedText, secretKey);
    }
	
	/**
	 * 
	 * @param encryptedText
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = getCipher();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
	
	static SecretKey secretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }
	
	/**
	 * 
	 * @param encodedKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	static SecretKey secretKey(String encodedKey) throws NoSuchAlgorithmException {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        // rebuild key using SecretKeySpec
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return secretKey;
    }
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	static String secretKeyText() throws NoSuchAlgorithmException {
        // create new key
        SecretKey secretKey = secretKey();
        // get base64 encoded version of the key
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
	
	/**
	 * 
	 * @param plainText
	 * @param secretKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = getCipher();
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }
	/**
	 * 
	 * @param plainText
	 * @param encodedKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText, String encodedKey) throws Exception {
        SecretKey secretKey = secretKey(encodedKey);
        return encrypt(plainText, secretKey);
    }
	
	/**
	 * 
	 * @return
	 */
	public static String generateIdAsString() {
        return replaceHyphens(UUID.randomUUID().toString());
    }
	
	/**
	 * 
	 * @param codefrom
	 * @return
	 */
	public static String replaceHyphens(String codefrom) {
        return codefrom.replaceAll("-", "");
    }
	
	public enum listOfCountries {
		TANZANIA,
		KENYA,
		RWANDA,
		UGANDA
	}
}
