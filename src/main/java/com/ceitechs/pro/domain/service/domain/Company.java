/**
 * company details for non-individual pros
 */
package com.ceitechs.pro.domain.service.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
public class Company {
	private String companyReferenceId; 
	private String businessName;
	private Pro pro;

}
