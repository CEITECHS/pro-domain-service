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
@Document(collection="projects")
@TypeAlias("projects")
public class Project {
	@Id
	private String projectReferenceId;
	
	@NotEmpty(message = "project - projectName can not be null or empty.")
	private String projectName;
	
	private LocalDate startDate; // the first date to a contractor started on this project Ex. 2016-05-30
	private LocalDate endDate; // latest/last date to a contractor worked on this project Ex. 2016-07-30
	
	private List<String> offeredServices = new ArrayList<>(); //services offered by this pro on this particular project
	private List<Attachment> attachments = new ArrayList<>(); // a list a attachments a project has to in order to show more of his/her work.
	
	@NotEmpty(message = "project - client can not be null or empty.")
	private Client client;
}
