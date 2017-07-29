/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
@Document(collection="serviceOfferings")
@TypeAlias("serviceOfferings")
public class ServiceOffering {
	private String serviceReferenceId; // Unique identifier for the service
	private String serviceDescription; // description of the offered service
	private String proReferenceId; // owner of the service
	private String subPhaseReferenceId;
	private List<String> projectReferenceId = new ArrayList<>();
}
