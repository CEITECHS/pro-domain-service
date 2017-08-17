/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.Address;
import com.ceitechs.pro.domain.service.domain.Pro;
import com.ceitechs.pro.domain.service.domain.ProProfile;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class ProRepositoryTest extends AbstractProDomainServiceIntegrationTest{
	@Autowired
	private ProRepository proRepository;
	private static String proReferenceId;
	private static final String EMAIL_ADDRESS = "test@pro.com";
	private static final String VERIFICATION_CODE = "verificationCode";
	
	/**
	 * A quick pro signUp providing 
	 * 	username as emailAdress 
	 * 	and password.
	 * 	Also a random generated verification code will be stored.
	 * 	
	 */
	@Test
	public void testSingUpPro() {
		Pro signedUpPro = proSignUp();
		Assert.assertEquals("The returned emailAddress must be same as the one used during signUp: ", 
				signedUpPro.getProProfile().getEmailAddress(), EMAIL_ADDRESS);
		Assert.assertEquals("ProReferenceId must be same as the one generated during signUp: ", 
				signedUpPro.getProReferenceId(), proReferenceId);
		Assert.assertFalse("The signedUp pro must have not been verified yet: ", signedUpPro.getProProfile().isVerified());
		Assert.assertEquals("The returned verification code must be same as the one used during signUp: ",
				signedUpPro.getProProfile().getVerificationCode(), VERIFICATION_CODE);
		
	}
	
	@Test
	public void testProVerification() {
		Pro signedUpPro = proSignUp();
		signedUpPro.getProProfile().setVerified(true);
		Pro verifiedPro = proRepository.save(signedUpPro);
		Assert.assertTrue("The returned Pro must have been verified: ", verifiedPro.getProProfile().isVerified());
	}
	
	@Test
	public void testSavePro() {
		Pro proSaved = proRepository.save(createPro());
		Assert.assertEquals(proSaved.getProReferenceId(), proReferenceId);
	}
	
	private Pro proSignUp() {
		ProProfile profile = new ProProfile();
		profile.setEmailAddress(EMAIL_ADDRESS);
		profile.setPassword("password123");
		profile.setVerificationCode(VERIFICATION_CODE);
		
		Pro pro = new Pro();
		pro.setProProfile(profile);
		pro.setProReferenceId(proReferenceId);
		
		return proRepository.save(pro);
	}
	
	private static Pro createPro() {
		Pro pro = new Pro();
		pro.setProReferenceId(proReferenceId);
		pro.setFirstName("fName");
		pro.setMiddleName("MName");
		pro.setLastName("LName");
		pro.setAddress(new Address());
		
		ProProfile profile = new ProProfile();
		profile.setVerified(true);
		pro.setProProfile(profile);
		return pro;
	}
	
	@Before
	public void setUp() {
		proReferenceId = ProUtility.generateIdAsString();
		proRepository.deleteAll();
	}
	
}
