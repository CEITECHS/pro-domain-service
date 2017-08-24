/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.Attachment;
import com.ceitechs.pro.domain.service.util.ProUtility;


/**
 * @author vctrowino
 *
 */
public class AttachmentRepositoryTest extends AbstractProDomainServiceIntegrationTest {
	@Autowired
	private AttachmentRepository attachmentRepository;
	private static String[] parents = {ProUtility.generateIdAsString(), ProUtility.generateIdAsString(), ProUtility.generateIdAsString()};
	
	@Test
	public void testSave(){
	    Attachment attachment = createAttachment();
	    attachmentRepository.save(attachment);
	    Attachment savedAttachment = attachmentRepository.findOne(attachment.getAttachmentReferenceId());
	    Assert.assertEquals(attachment, savedAttachment);
	}
	
	@Test
    public void testRetrieveThumbnailsByParentReferenceId(){
        List<Attachment> attachments = createAttachments();
        attachmentRepository.save(attachments);
        List<Attachment> savedAttachments = attachmentRepository.findByParentReferenceIdAndCategoryAndThumbnailTrueAndActiveTrue(parents[0],
        		Attachment.attachmentCategoryType.PHOTO_ID.name());
        Assert.assertNotNull(savedAttachments);
        Assert.assertTrue("retrieved size should match saved size", savedAttachments.stream()
                .filter(Attachment::isThumbnail).filter(Attachment::isActive).collect(Collectors.toList()).size()>0);
    }
	
	@Test
	public void testFindByParentReferenceIdAndCategoryAndActiveTrue() {
		List<Attachment> attachments = createAttachments();
        attachmentRepository.save(attachments);
        List<Attachment> savedAttachments = attachmentRepository.findByParentReferenceIdAndCategoryAndActiveTrue(parents[0],
        		Attachment.attachmentCategoryType.PROFILE_PICTURE.name());
        Assert.assertNotNull(savedAttachments);
        Assert.assertTrue("retrieved size should be 1", savedAttachments.stream()
                .filter(Attachment::isActive).collect(Collectors.toList()).size()==1);
		
	}
	
	public static Attachment createAttachment(){
        Attachment attachment = new Attachment();
        attachment.setBucket("test");
        attachment.setCategory(Attachment.attachmentCategoryType.PHOTO_ID.name());
        attachment.setDescription("test - attachment");
        attachment.setAttachmentReferenceId(ProUtility.generateIdAsString());
        attachment.setParentReferenceId(ProUtility.generateIdAsString());
        try {
            MockMultipartFile multiPartFile = new MockMultipartFile(resource.getFilename(), resource.getFilename(), null, resource.getInputStream());
            attachment.setAttachment(multiPartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }
	
	/**
     * creates 10 attachments under 3 distinct parent of the same category
     * @return
     */
    private static List<Attachment> createAttachments() {
        List<Attachment> attachments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Attachment attachment = createAttachment();
            if (i % 2 == 0) {
                attachment.setParentReferenceId(parents[0]);
                if (i > 6) attachment.setActive(false);
                if (i == 6) attachment.setThumbnail(true);
            } else if (i % 3 == 0 && i % 2 != 0) {
                attachment.setParentReferenceId(parents[1]);
                if (i == 9) attachment.setThumbnail(true);
            }
            else attachment.setParentReferenceId(parents[2]);

            attachments.add(attachment);
        }
        attachments.get(0).setCategory(Attachment.attachmentCategoryType.PROFILE_PICTURE.name());
        return attachments;
    }
	
	@Before
	public void setUp() {
		attachmentRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}
}
