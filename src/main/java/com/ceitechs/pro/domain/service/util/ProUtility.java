/**
 * 
 */
package com.ceitechs.pro.domain.service.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
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
	
	/**
	 * 
	 * @param encodedKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static SecretKey secretKey(String encodedKey) throws NoSuchAlgorithmException {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        // rebuild key using SecretKeySpec
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return secretKey;
    }

}
