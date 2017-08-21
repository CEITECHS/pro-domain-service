/**
 * 
 */
package com.ceitechs.pro.domain.service;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ceitechs.pro.domain.service.config.ProDomainServiceConfig;

/**
 * @author vctrowino
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {
		"pro.domain.service.db.host.name = localhost:27017", 
		"pro.domain.service.db.password = proPreProdWriteUsrPass10", 
		"property.holding.hours=48",
        "pro.domain.service.db.user = proWriteUser", 
        "pro.domain.service.db.name = prodb", "pro.domain.service.bucket.name = picture",
        "magic.key=5rGXHCU2yoGTn600Gz9i5A==",
        "templates.root.uri = https://raw.githubusercontent.com/CEITECHS/pango-configs/master/templates/",
        "user.verification.uri=https://www.chaguapango.com/accountVerification/",
        "from.email.address=vctrowino@gmail.com",
        "mail.smtp.host=smtp.gmail.com",
        "mail.smtp.port=587",
        "mail.smtp.protocal=",
        "mail.smtp.user=KrWdWgeYKiIMQqEUVbWMG6xUaIIsDfPJhEVr/VsXUOQ=",
        "mail.smtp.password=NkCHfPStANtsWkcTbsoaDQ==",
        "mail.smtp.auth=true",
        "mail.smtp.starttls.enable=true", 
        "access.key.id=AKIAJLBAJNUMLHZIUZEA", "secret.key.id=9xinRhvCzMO10uBnmFa0IqSnQFTuNk9JjFlKxkHHOdYFRFJTagTG8wKs5i4X+dQK",
        "s3.signedurl.timeout.milliseconds=3600000", "s3.attachments.bucketname=pango-attachments"})
@ContextConfiguration(classes = {ProDomainServiceConfig.class})
@Ignore
public class AbstractProDomainServiceIntegrationTest {
	protected static final Resource resource = new ClassPathResource("ceitechs.png");

}
