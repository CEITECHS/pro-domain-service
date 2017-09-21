/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	/**
	 * referenceId of the owner of the attachment
	 * might be proReferenceId, trainingReferenceId, projectReferenceId etc
	 * according to attachmentCategoryType
	 */
	@Indexed
	@NotEmpty(message = "attachment - parentreferenceId can not be null or empty.")
	private String parentReferenceId; 
	
	@Indexed
	@NotEmpty(message = "attachment-category can not be null or empty")
	private String category;
	
	private String description;
	private boolean thumbnail = false;
	private boolean active = true;
	
	@Transient
	private String url;
	
	@Indexed
	@NotEmpty(message = "attachment - proReferenceId can not be empty or null")
	private String proReferenceId; // uploaded by
	
	private LocalDate createdDate = LocalDate.now();
	
	public enum attachmentCategoryType {
		PHOTO_ID,
		PROFILE_PICTURE,
		TRAINING,
		PROJECT
	}
	
	private String bucket; // directory stored
	
	@JsonIgnore
    @Transient
    private MultipartFile attachment;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(attachmentReferenceId, that.attachmentReferenceId) &&
                Objects.equals(parentReferenceId, that.parentReferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachmentReferenceId, parentReferenceId);
    }
}
