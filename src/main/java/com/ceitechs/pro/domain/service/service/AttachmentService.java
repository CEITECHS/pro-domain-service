/**
 * 
 */
package com.ceitechs.pro.domain.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ceitechs.pro.domain.service.domain.Attachment;
import com.ceitechs.pro.domain.service.domain.Attachment.attachmentCategoryType;
import com.ceitechs.pro.domain.service.domain.Pro;
import com.ceitechs.pro.domain.service.repositories.AttachmentRepository;
import com.ceitechs.pro.domain.service.util.ProUtility;
import static java.util.stream.Collectors.toList;

/**
 * @author vctrowino
 *
 */
public interface AttachmentService {
	/**
	 * 
	 * @param pro
	 * @param attachment
	 * @return
	 */
	Optional<Attachment> storeAttachment(Pro pro, Attachment attachment);
	/**
	 * 
	 * @param attachmentReferenceId
	 * @return
	 */
    Optional<Attachment> retrieveAttachmentBy(String attachmentReferenceId);
    /**
     * 
     * @param attachmentReferenceId
     * @return
     */
    Optional<Attachment> removeAttachmentBy(String attachmentReferenceId);
    /**
     * 
     * @param parentReferenceId
     * @return
     */
    Optional<Attachment> retrieveProfilePictureBy(String parentReferenceId);
    /**
     * 
     * @param pro
     * @return
     */
    List<Attachment> retrieveAttachmentsBy(Pro pro);
    /**
     * 
     * @param parentReferenceId
     * @param category
     * @return
     */
    List<Attachment> retrieveAttachmentsBy(String parentReferenceId, String category);
    /**
     * 
     * @param parentReferenceId
     * @param category
     * @return
     */
    List<Attachment> retrieveThumbnailAttachmentsBy(String parentReferenceId, String category);
    /**
     * 
     * @param parentReferenceIds
     * @param category
     * @return
     */
    List<Attachment> retrieveAttachmentsBy(List<String> parentReferenceIds, String category);
}

@Service
class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final FileStorageService fileStorageService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //Size in KB
    private static final long MAX_SIZE = 1024;

    private final Executor executor = Executors.newFixedThreadPool(100,
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true); // use daemon threads, - they don't prevent termination of program
                return t;
            });

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository, FileStorageService fileStorageService) {
        this.attachmentRepository = attachmentRepository;
        this.fileStorageService = fileStorageService;
    }

	@Override
	public Optional<Attachment> storeAttachment(Pro pro, Attachment attachment) {
		Assert.notNull(attachment , "Attachment; can not be null or empty");
        Assert.notNull(pro, "pro uploading an attachment can not be null");
        Assert.hasText(pro.getProReferenceId(), "proReferenceId : can not be null or empty");
        Assert.hasText(attachment.getCategory(),"attachment-category can not be null or empty");
        Assert.hasText(attachment.getParentReferenceId(), "attachment parentReferenceId can not be null or empty");
        Assert.notNull(attachment.getAttachment(), "attachment-to-upload can not be null or empty");
        Assert.isTrue(Arrays.stream(Attachment.attachmentCategoryType.values()).map(Enum::name).anyMatch(cat -> cat.equals(attachment.getCategory().toUpperCase())),"un-supported-category");
        Assert.isTrue(MAX_SIZE >= (attachment.getAttachment().getSize()/MAX_SIZE) ,"attachment-size can not be greater than 1MB");
        attachment.setProReferenceId(pro.getProReferenceId());

        //set Id
        attachment.setAttachmentReferenceId(ProUtility.generateIdAsUUID());
        attachment.setCategory(attachment.getCategory().toUpperCase());

         Attachment attachmentToLoad;
         try {
             //1. store actual file
             attachmentToLoad = fileStorageService.storeFile(attachment);
             //2. store metadata
             attachmentToLoad = attachmentRepository.save(attachmentToLoad);
             //3. resolve urls
             attachmentToLoad.setUrl(fileStorageService.resolveUrl(attachmentToLoad));
         }catch (Exception ex){
             logger.error("Error occurred during attachment upload " + ex.getMessage(), ex);
             throw new RuntimeException("Error occurred during attachment upload ", ex);
         }
        return Optional.ofNullable(attachmentToLoad);
	}

	@Override
	public Optional<Attachment> retrieveAttachmentBy(String attachmentReferenceId) {
		Assert.hasText(attachmentReferenceId, "attachmentReferenceId can not be null or empty");
	    Attachment attachment = attachmentRepository.findByAttachmentReferenceIdAndActiveTrue(attachmentReferenceId);
	    try {
	    	attachment.setUrl(fileStorageService.resolveUrl(attachment));
	    } catch (Exception ex) {
	    	logger.error("Error occurred during attachment-url resolve " + ex.getMessage(), ex);
	    }
	    return Optional.ofNullable(attachment);
	}

	@Override
	public Optional<Attachment> removeAttachmentBy(String attachmentReferenceId) {
		Assert.hasText(attachmentReferenceId, "attachmentReferenceId can not be null or empty");
        Attachment attachment = attachmentRepository.findOne(attachmentReferenceId);
        Assert.notNull(attachment, "attachmentReferenceId doesn't exists ");
        try{
            fileStorageService.removeFile(attachment);
            attachmentRepository.delete(attachmentReferenceId);
        }catch (Exception ex){
            logger.error("Error occurred during attachment remove/deletion " + ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while trying to delete an attachment ", ex);
        }
        return Optional.ofNullable(attachment);
	}

	@Override
	public Optional<Attachment> retrieveProfilePictureBy(String parentReferenceId) {
		Assert.hasText(parentReferenceId, "ParentReferenceId can not be null or empty");
        List<Attachment> attachments = attachmentRepository.findByParentReferenceIdAndCategoryAndActiveTrue(parentReferenceId,
        		attachmentCategoryType.PROFILE_PICTURE.toString());
        Attachment attachment = null;
        try {
        	attachment = attachments.get(0);
            attachment.setUrl(fileStorageService.resolveUrl(attachment));
        } catch (Exception ex) {
            logger.error("Error occurred during attachment-url resolve " + ex.getMessage(), ex);
        }
        return Optional.ofNullable(attachment);
	}

	@Override
	public List<Attachment> retrieveAttachmentsBy(Pro pro) {
		Assert.notNull(pro, "pro can not be null");
        Assert.hasText(pro.getProReferenceId(), "proReferenceId can not be null or empty");
        List<Attachment> attachments = attachmentRepository.findByParentReferenceIdAndActiveTrueOrderByCreatedDateDesc(pro.getProReferenceId());
        return CollectionUtils.isNotEmpty(attachments) ? resolveUrls(attachments) : attachments;
	}

	@Override
	public List<Attachment> retrieveAttachmentsBy(String parentReferenceId, String category) {
		Assert.hasText(parentReferenceId, "parentReferenceId: can not be null or empty");
        Assert.hasText(category, "category: can not be null or empty");
        List<Attachment> attachments = attachmentRepository.findByParentReferenceIdAndCategoryAndActiveTrue(parentReferenceId, category.toUpperCase());
        return CollectionUtils.isNotEmpty(attachments) ? resolveUrls(attachments) : attachments;
	}

	@Override
	public List<Attachment> retrieveThumbnailAttachmentsBy(String parentReferenceId, String category) {
		Assert.hasText(parentReferenceId, "parent-reference-ids; can not be null or empty");
        Assert.hasText(category, "category: can not be null or empty");
        List<Attachment> attachments = attachmentRepository.findByParentReferenceIdAndCategoryAndThumbnailTrueAndActiveTrue(parentReferenceId,category.toUpperCase());
        return CollectionUtils.isNotEmpty(attachments)? resolveUrls(attachments):attachments;
	}

	@Override
	public List<Attachment> retrieveAttachmentsBy(List<String> parentReferenceIds, String category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Attachment> resolveUrls(List<Attachment> attachments) {
        Assert.notEmpty(attachments, "attachment-list can not be null or empty");
        List<CompletableFuture<Attachment>> attachmentsCompletableFutures =
                attachments.stream()
                        .map(attachment -> CompletableFuture.supplyAsync(
                                () -> {
                                    try {
                                        attachment.setUrl(fileStorageService.resolveUrl(attachment));
                                    } catch (Exception ex) {
                                        logger.error("Error occurred during attachment-url resolve " + ex.getMessage(), ex);
                                    }
                                    return attachment;

                                }, executor
                        )).collect(toList());
        return attachmentsCompletableFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
