/**
 * 
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
public class ServiceOffering {
	private String serviceReferenceId; // Unique identifier for the service
	private String serviceDescription; // description of the offered service
	private Pro pro; // owner of the service
	private SubPhase subPhase;

}
