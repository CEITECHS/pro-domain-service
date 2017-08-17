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
	//Attachment findByProReferenceIdAndActiveTrueAndCategoryProfilePicture(String proReferenceId);
	List<Attachment> findByParentReferenceIdAndActiveTrue();

}
