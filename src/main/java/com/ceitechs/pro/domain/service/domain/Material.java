/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;


import java.time.LocalDate;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
@ToString
@Document(collection="materials")
@TypeAlias("materials")
public class Material {
	@Id
	private String materialReferenceId;
	
	@NotEmpty(message = "Material name can not be null or empty.")
	private String materialName;
	
	private String description;
	
	@Indexed
	@NotEmpty(message = "material - createdBy can not be null or empty.")
	private String createdBy; //userReferenceId of person who created this.
	
	private LocalDate createdDate = LocalDate.now();
}
