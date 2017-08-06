/**
 * 
 */
package com.ceitechs.pro.domain.service.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
@Configuration
@Import({ ProDomainServiceMongoConfiguration.class })
@ComponentScan(basePackages = "com.ceitechs.pro.domain.service.service")
public class ProDomainServiceConfig {
	@Value("${mail.smtp.host}")
	private String mailHost;

	@Value("${mail.smtp.port}")
	private int mailPort;

	@Value("${mail.smtp.protocal}")
	private String mailProtocal;

	@Value("${mail.smtp.user}")
	private String mailUser;

	@Value("${mail.smtp.password}")
	private String mailPassword;

	@Value("${mail.smtp.auth}")
	private boolean mailAuth;

	@Value("${mail.smtp.starttls.enable}")
	private boolean mailStarttls;

	@Value("${magic.key}")
	private String appliedKey;

	@Value("${templates.root.uri}")
	private String templatesRoot;

	@Value("${access.key.id}")
	private String keyId;

	@Value("${secret.key.id}")
	private String secretKey;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Lazy(true)
	public JavaMailSenderImpl javaMailSenderImpl() throws Exception {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(mailHost);
		mailSenderImpl.setPort(mailPort);
		
		mailSenderImpl.setUsername(ProUtility.decrypt(mailUser, appliedKey));
		mailSenderImpl.setPassword(ProUtility.decrypt(mailPassword, appliedKey));
		
		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", mailAuth);
        javaMailProps.put("mail.smtp.starttls.enable", mailStarttls);
        mailSenderImpl.setJavaMailProperties(javaMailProps);
        
		return mailSenderImpl;
	}

}
