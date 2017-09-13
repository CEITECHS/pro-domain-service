/**
 * 
 */
package com.ceitechs.pro.domain.service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author vctrowino
 *
 */
public interface MailService {
	/**
     *  sends email to one or many recipients based on {@link EmailModel}
     * @param emailModel
     */
     <T> void sendEmail(EmailModel<T> emailModel);

}

@Service
class MailServiceImpl implements MailService {

    @Value("${from.email.address}")
    private String defaultFromEmail;


    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    protected static final Resource resource = new ClassPathResource("templates/logo.png");

    private final JavaMailSender mailSender;

    private final VelocityEngine velocityEngine;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, VelocityEngine velocityEngine) {
        this.mailSender = mailSender;
        this.velocityEngine = velocityEngine;
    }

    /**
     * sends email to one or many recipients based on {@link EmailModel}
     *
     * @param emailModel
     */
    @Override
    public <T> void sendEmail(EmailModel<T> emailModel) {

        Assert.notNull(emailModel, "EmailModel can not be null");
        Assert.hasText(emailModel.getSubject(), "email subject can not be empty or null");
        Assert.isTrue(emailModel.getBccRecipients().length > 0 || emailModel.getRecipients().length >0 ,"recipient list can not be null or empty");
        Assert.notNull(emailModel.getModel() , "model can not be null ");
        Assert.isTrue(emailModel.getModel() instanceof String || StringUtils.hasText(emailModel.getTemplate()),"");
        Assert.isTrue(StringUtils.hasText(emailModel.getFromEmail()) || StringUtils.hasText(defaultFromEmail), "from email can not be null or empty");


        mailSender.send(mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            if (emailModel.getRecipients().length > 0) message.setTo(emailModel.getRecipients());
            if (emailModel.getBccRecipients().length > 0) message.setBcc(emailModel.getBccRecipients());
            if (emailModel.getCopiedRecipients().length > 0) message.setCc(emailModel.getCopiedRecipients());
            message.setFrom(StringUtils.hasText(emailModel.getFromEmail())? emailModel.getFromEmail():defaultFromEmail);
            message.setSentDate(new Date());
            message.setSubject(emailModel.getSubject());

            StringBuilder emailText = null;
            if (emailModel.getModel() instanceof String) {
                // sending plain text message without template
                emailText = new StringBuilder((String) emailModel.getModel());
            } else if (StringUtils.hasText(emailModel.getTemplate())) {
                // sending email using a template
                String templatePath = new String(emailModel.getTemplate() + ".vm");
                Map<String, Object> model = new HashMap<>();
                model.put("model", emailModel.getModel());
                emailText = new StringBuilder(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, "UTF-8", model));
            }

            message.setText(emailText.toString(), true);

        });
    }
}
