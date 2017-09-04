/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
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
	@Id
	private String serviceReferenceId; // Unique identifier for the service
	private String serviceDescription; // description of the offered service
	
	@Indexed
	@NotEmpty(message = "ServiceOffering - proReferenceId can not be null or empty.")
	private String proReferenceId; // owner of the service
	
	private String subPhaseReferenceId;
	private List<String> projectReferenceIds = new ArrayList<>();
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOffering that = (ServiceOffering) o;
        return Objects.equals(serviceReferenceId, that.serviceReferenceId) &&
                Objects.equals(proReferenceId, that.proReferenceId) && 
                Objects.equals(subPhaseReferenceId, that.subPhaseReferenceId);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(serviceReferenceId, proReferenceId, subPhaseReferenceId);
    }
}
