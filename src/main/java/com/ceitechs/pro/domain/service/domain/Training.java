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
 * @author vctro
 *
 */
@Getter
@Setter
public class Training {
	private String trainingReferenceId;
	private String institution;
	private LocalDate startDate; // Start date of the program Ex. 2016-05-30
	private LocalDate endDate; // latest/last date to a program Ex. 2016-07-30
	private String award; // Award awarded EX. Degree, certificate.
	private String descipline; // Domain of your training Ex. IT 
	private List<Attachment> attachments = new ArrayList<>(); // a list of attachments to support this training.
	private Pro pro;
}
