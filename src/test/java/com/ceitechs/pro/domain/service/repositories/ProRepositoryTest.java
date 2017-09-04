/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.Address;
import com.ceitechs.pro.domain.service.domain.Phone;
import com.ceitechs.pro.domain.service.domain.Pro;
import com.ceitechs.pro.domain.service.domain.ProProfile;
import com.ceitechs.pro.domain.service.domain.Training;
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
	private static final String ADDRESS_LINE1 = "addressLine1";
	private static final String ADDRESS_LINE2 = "addressLine2";
	private static final String DISTRICT = "district";
	private static final String REGION = "region";
	private static final String PHONE_NUMBER1 = "1234567890";
	private static final String PHONE_NUMBER2 = "0987654321";
	private static List<Phone> phones = new ArrayList<>();
	private static List<Training> trainings = new ArrayList<>();
	{
		Phone phone1 = new Phone();
		phone1.setPhoneNumber(PHONE_NUMBER1);
		
		Phone phone2 = new Phone();
		phone2.setPhoneNumber(PHONE_NUMBER2);
		
		phones.add(phone1);
		phones.add(phone2);
		
		Training training = new Training();
		training.setStartDate(LocalDate.parse("2006-07-01"));
		training.setEndDate(LocalDate.parse("2009-12-09"));
		training.setInstitution("institution");
		training.setDescipline("descipline");
		training.setAward("award");
		training.setTrainingReferenceId(ProUtility.generateIdAsString());
		
		trainings.add(training);
		
	}
	
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
		Assert.assertEquals("The returned operatingAs must be same as defaulted value: ",
				signedUpPro.getOperatingAs(), Pro.operaratingAsCategoryType.INDIVIDUAL.toString());
		
	}
	
	@Test
	public void testProVerification() {
		Pro signedUpPro = proSignUp();
		signedUpPro.getProProfile().setVerified(true);
		Pro verifiedPro = proRepository.save(signedUpPro);
		Assert.assertTrue("The returned Pro must have been verified: ", verifiedPro.getProProfile().isVerified());
	}
	
	@Test
	public void testUpdateIntroText() {
		Pro signedUpPro = proSignUp();
		signedUpPro.setIntroText("introText");
		Pro updatedPro = proRepository.save(signedUpPro);
		Assert.assertEquals("The returned introText must be same as the one used during the update process: ", 
				updatedPro.getIntroText(), "introText");
	}
	
	@Test
	public void testUpdateProBasicInfo() {
		Pro signedUpPro = proSignUp();
		
		Pro pro = updateProWithBasicInfo(signedUpPro);
		Pro updatedPro = proRepository.save(pro);
		Assert.assertEquals("The returned firstName must be same as the one used during the update process: ", 
				updatedPro.getFirstName(), "FName");
		Assert.assertEquals("The returned middleName must be same as the one used during the update process: ", 
				updatedPro.getMiddleName(), "MName");
		Assert.assertEquals("the returned lastName must be same as the one used during the update process: ", 
				updatedPro.getLastName(), "LName");
	}
	@Test
	public void testUpdateProAddress() {
		Pro signedUpPro = proSignUp();
		Address address = createProAddress();
		signedUpPro.setAddress(address);
		Pro updatedPro = proRepository.save(signedUpPro);
		Assert.assertEquals("The returned address must be same as the one used during the update process: ", 
				updatedPro.getAddress(), address);
	}
	
	@Test
	public void testUpdateProPhones() {
		Pro signedUpPro = proSignUp();
		signedUpPro.setPhones(phones);
		Pro updatedPro = proRepository.save(signedUpPro);
		Assert.assertEquals("The returned phones must be same as the one used during the update process: ", 
				updatedPro.getPhones(), phones);
		Assert.assertTrue("The returned phones size must be greater than 1", updatedPro.getPhones().size()>1);
	}
	@Test
	public void testUpdateProTrainings() {
		Pro signedUpPro = proSignUp();
		signedUpPro.setTrainings(trainings);
		Pro updatedPro = proRepository.save(signedUpPro);
		Assert.assertEquals("The returned trainings must be same as the one used during the update process: ", 
				updatedPro.getTrainings(), trainings);
		Assert.assertTrue("The returned training size must be greater than 0", updatedPro.getTrainings().size()>0);
	}
	
	@Test
	public void testDeleteAllProTrainings() {
		Pro signedUpPro = proSignUp();
		signedUpPro.setTrainings(trainings);
		Pro updatedPro = proRepository.save(signedUpPro);
		Assert.assertEquals("The returned trainings must be same as the one used during the update process: ", 
				updatedPro.getTrainings(), trainings);
		Assert.assertTrue("The returned training size must be greater than 0", updatedPro.getTrainings().size()>0);
		Pro proWithTraining = proRepository.findOne(updatedPro.getProReferenceId());
		proWithTraining.setTrainings(new ArrayList<>());
		Pro proWithNoTraining = proRepository.save(proWithTraining);
		
		Assert.assertTrue("Training list must be empty ", proWithNoTraining.getTrainings().isEmpty());
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
	
	private static Address createProAddress() {
		Address address = new Address();
		address.setAddressLine1(ADDRESS_LINE1);
		address.setAddressLine2(ADDRESS_LINE2);
		address.setCountry(ProUtility.listOfCountries.TANZANIA.toString());
		address.setDistrict(DISTRICT);
		address.setRegion(REGION);
		return address;
	}
	
	private static Pro updateProWithBasicInfo(Pro pro) {
		pro.setFirstName("FName");
		pro.setMiddleName("MName");
		pro.setLastName("LName");
		return pro;
	}
	
	@Before
	public void setUp() {
		proReferenceId = ProUtility.generateIdAsString();
		proRepository.deleteAll();
		
	}
	@After
	public void tearDown() {
		phones.clear();
		trainings.clear();
	}
	
}
