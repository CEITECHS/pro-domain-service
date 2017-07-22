/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@ToString
@Getter
@Setter
@Document(collection="proffessionals")
@TypeAlias("proffessionals")
public class Pro {
	@Id
	private String proReferenceId;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private String introText;
	
	private String emailAddress;
	private ArrayList<Phone> phones = new ArrayList<>(5);
	private Address address;
	
	private String operatingAs; // operating model of a pro , allowed values is [Company or Individual] Ex. Individual
	private Company company; // company details for non-individual pros
	
	private ProProfile proProfie;
	private List<Training> trainings = new ArrayList<>();
	private List<ServiceOffering> services = new ArrayList<>();
	
	private LocalDate createdDate = LocalDate.now();
}
