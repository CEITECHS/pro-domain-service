/**
 * company details for non-individual pros
 */
package com.ceitechs.pro.domain.service.domain;

import java.util.ArrayList;
import java.util.List;

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
public class Company {
	private String businessName;
	private List<Phone> phones = new ArrayList<>();
	private Address address;
	private String emailAddress;	
}
