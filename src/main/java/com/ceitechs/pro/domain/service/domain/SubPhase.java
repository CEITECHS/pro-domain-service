/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
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
import lombok.ToString;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
@ToString
@Document(collection="subphases")
@TypeAlias("subphases")
public class SubPhase {
	@Id
	private String subPhaseReferenceId;
	
	@NotEmpty(message = "subphase - subPhaseName can not be null or empty.")
	private String subPhaseName;
	
	private String description;
	
	@Indexed
	@NotEmpty(message = "subphase - phaseReferenceId can not be null or empty.")
	private String phaseReferenceId;
	
	private List<Material> materials = new ArrayList<>();

	@Indexed
	@NotEmpty(message = "subphase - createdBy can not be null or empty.")
	private String createdBy; //userReferenceId of person who created this.
	
	private LocalDate createdDate = LocalDate.now();
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubPhase that = (SubPhase) o;
        return Objects.equals(subPhaseReferenceId, that.subPhaseReferenceId) &&
                Objects.equals(phaseReferenceId, that.phaseReferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subPhaseReferenceId, phaseReferenceId);
    }
}
