/**
 * 
 */
package com.ceitechs.pro.domain.service.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.Attachment;
import com.ceitechs.pro.domain.service.domain.Pro;
import com.ceitechs.pro.domain.service.repositories.AttachmentRepository;
import com.ceitechs.pro.domain.service.repositories.AttachmentRepositoryTest;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class AttachmentServiceTest extends AbstractProDomainServiceIntegrationTest{
	@Autowired
    private AttachmentService attachmentService;
    @Autowired
    private AttachmentRepository attachmentRepository;
    
    @Test(expected = RuntimeException.class)
    public void storeAttachmentWithoutActualAttachmentTest() {
        Attachment attachment = AttachmentRepositoryTest.createAttachment();
        attachment.setAttachment(null);
        Pro pro = new Pro();
        pro.setProReferenceId(ProUtility.generateIdAsString());
        attachmentService.storeAttachment(pro, attachment);
    }
    
    @Test
    public void storeAttachmentWithActualAttachmentTest() throws IOException {
        attachmentRepository.deleteAll();
        Attachment attachment = AttachmentRepositoryTest.createAttachment();
        Pro pro = new Pro();
        pro.setProReferenceId(ProUtility.generateIdAsString());
        attachment.setAttachment(new MockMultipartFile(resource.getFilename(), resource.getInputStream()));
        Optional<Attachment> attachmentOptional = attachmentService.storeAttachment(pro, attachment);
        assertTrue(attachmentOptional.isPresent());
        assertNotNull(attachmentOptional.get().getUrl());
        System.out.println(attachmentOptional.get().getUrl());
    }

}
