/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@Document(collection="phases")
@TypeAlias("phases")
public class Phase {
	@Id
	private String phaseReferenceId;
	
	@NotEmpty(message = "phase name can not be null or empty.")
	private String phaseName;
	
	private String description;
	private List<SubPhase> subPhases = new ArrayList<>();
	
	@Indexed
	@NotEmpty(message = "phase - createdBy can not be null or empty.")
	private String createdBy; //userReferenceId of person who created this.
	
	private LocalDate createdDate = LocalDate.now();
}
