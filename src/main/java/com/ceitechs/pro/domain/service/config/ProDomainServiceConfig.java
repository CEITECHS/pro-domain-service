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
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
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
	/**
	 * 
	 * @return
	 */
	
	@Bean
    public VelocityEngineFactoryBean velocityEngine() {
        VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
        Properties velocityProperties = new Properties();
        velocityProperties.setProperty("resource.loader", "url"); //jar, class, file
        velocityProperties.setProperty("url.resource.loader.class","org.apache.velocity.runtime.resource.loader.URLResourceLoader");//ClasspathResourceLoader
        velocityProperties.setProperty("url.resource.loader.root",templatesRoot);
        velocityProperties.setProperty("url.resource.loader.cache","true");
        //velocityProperties.setProperty("url.resource.loader.modificationCheckInterval","5");
        velocityEngineFactoryBean.setVelocityProperties(velocityProperties);
        return velocityEngineFactoryBean;
    }
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
    public AmazonS3 amazonS3Client() throws Exception {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(keyId, ProUtility.decrypt(secretKey,appliedKey));
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
