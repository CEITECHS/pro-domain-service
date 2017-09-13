/**
 * 
 */
package com.ceitechs.pro.domain.service.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.Pro;
import com.ceitechs.pro.domain.service.domain.ProProfile;

/**
 * @author vctrowino
 *
 */
public class EmailServiceTest extends AbstractProDomainServiceIntegrationTest {
	@Autowired MailService mailService;
	private String[] developerEmails = new String[]{"vctrowino@yahoo.com"};
	//"iddyiam@gmail.com","abhikumar.singh@gmail.com",
	
    @Test
    public void sendPlainTextEmailTest(){
        EmailModel<String> emailModel = new EmailModel<>();
        emailModel.setBccRecipients(developerEmails);
        emailModel.setSubject("DProz Test Email");
        StringBuilder str = new StringBuilder("This is a test email from DProz service");
        str.append(System.lineSeparator());
        str.append("Cheers");
        str.append(System.lineSeparator());
        str.append("Dproz Team");
        emailModel.setModel(str.toString());
        mailService.sendEmail(emailModel);
    }

    @Test
    public void sendTemplateEMailTest(){
        EmailModel<Pro> emailModel = new EmailModel<>();
        Pro pro = new Pro();
        
        ProProfile profile = new ProProfile();
        profile.setEmailAddress("vctrowino@yahoo.com");
        
        pro.setProProfile(profile);
        pro.setFirstName("uvoa");
        pro.setLastName("The Lucy One");
        emailModel.setModel(pro);
        emailModel.setSubject("DProz Test Email using a github hosted template");
        emailModel.setBccRecipients(developerEmails);
        emailModel.setTemplate("registration-confirmation");
        mailService.sendEmail(emailModel);
    }

}
