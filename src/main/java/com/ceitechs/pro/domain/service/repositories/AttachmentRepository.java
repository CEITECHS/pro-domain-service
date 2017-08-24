/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.Attachment;

/**
 * @author vctrowino
 *
 */
public interface AttachmentRepository extends MongoRepository<Attachment, String>{
	List<Attachment> findByParentReferenceIdAndActiveTrue();
	/**
	 * 
	 * @param parentReferenceId
	 * @param category
	 * @return
	 */
	List<Attachment> findByParentReferenceIdAndCategoryAndThumbnailTrueAndActiveTrue(String parentReferenceId, String category);
	/**
	 * 
	 * @param parentReferenceId
	 * @param category
	 * @return
	 */
	List<Attachment> findByParentReferenceIdAndCategoryAndActiveTrue(String parentReferenceId, String category);

}
