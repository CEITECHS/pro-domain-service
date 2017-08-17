/**
 * 
 */
package com.ceitechs.pro.domain.service.util;

import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author vctrowino
 *
 */
public class ProUtilityTest {
	
    @Test
    public void testSecretKeyTextEncryptDecrypt() throws Exception {
        String secretKey = ProUtility.secretKeyText();
        String encryptedText = ProUtility.encrypt("OmG", secretKey);
        Assert.assertTrue("OmG".equals(ProUtility.decrypt(encryptedText, secretKey)));
    }
    
	@Test
	public void testSecretKeyEncryptDecrypt() throws Exception {
		SecretKey secretKey = ProUtility.secretKey();
		String encryptedText = ProUtility.encrypt("plainText", secretKey);
		Assert.assertTrue("plainText".equals(ProUtility.decrypt(encryptedText, secretKey)));
	}
}
