/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
public class Attachment {
	private String referenceId;
	private String fileType;
	private double fileSize;
	private String category;
	private String description;
	private String url;
	private Pro pro;
	private LocalDate createdDate = LocalDate.now();
	public enum attachmentCategoryType {
		PHOTO_ID,
		PROFILE_PICTURE,
		OTHER
	}
}
