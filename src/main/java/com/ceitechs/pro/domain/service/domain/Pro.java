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
	
	private List<Phone> phones = new ArrayList<>(5);
	private Address address;
	
	/**
	 * operating model of a pro , allowed values is [Company or Individual] Ex. Individual
	 */
	private String operatingAs = operaratingAsCategoryType.INDIVIDUAL.toString(); 
	private Company company; // company details for non-individual pros
	
	private ProProfile proProfile;
	private List<Training> trainings = new ArrayList<>();
	
	private LocalDate createdDate = LocalDate.now();
	
	public enum operaratingAsCategoryType {
		INDIVIDUAL,
		COMPANY
	}
}
