/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
@Document(collection="attachments")
@TypeAlias("attachments")
public class Attachment {
	@Id
	private String attachmentReferenceId;
	
	@Indexed
	@NotEmpty(message = "attachment - parentreferenceId can not be null or empty.")
	private String parentReferenceId; //This is pro referenceId of the owner of the attachment.
	
	private String fileType;
	private double fileSize;
	
	@Indexed
	@NotEmpty(message = "attachment-category can not be null or empty")
	private String category;
	
	private String description;
	private boolean thumbnail = false;
	private boolean active = true;
	
	@Transient
	private String url;
	
	@Indexed
	@NotEmpty(message = "attachment - userReferenceId can not be empty or null")
	private String userReferenceId; // uploaded by
	
	private LocalDate createdDate = LocalDate.now();
	
	public enum attachmentCategoryType {
		PHOTO_ID,
		PROFILE_PICTURE,
		OTHER
	}
	
	private String bucket; // directory stored
}
