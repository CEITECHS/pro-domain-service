/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
public class Project {
	private String projectReferenceId;
	private String projectName;
	private LocalDate startDate; // the first date to a contractor started on this project Ex. 2016-05-30
	private LocalDate endDate; // latest/last date to a contractor worked on this project Ex. 2016-07-30
	private List<ServiceOffering> offeredServices = new ArrayList<>(); //services offered by this pro on this particular project
	private List<Attachment> attachments = new ArrayList<>(); // a list a attachments a project has to in order to show more of his/her work.
	private Client client;
}
