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
@Document(collection="projects")
@TypeAlias("projects")
public class Project {
	@Id
	private String projectReferenceId;
	
	@NotEmpty(message = "project - projectName can not be null or empty.")
	private String projectName;
	
	private LocalDate startDate; // the first date to a contractor started on this project Ex. 2016-05-30
	private LocalDate endDate; // latest/last date to a contractor worked on this project Ex. 2016-07-30
	
	@Indexed
	@NotEmpty(message = "project - proReferenceId can not be null or empty.")
	private String proReferenceId; // owner of the project
	
	private List<String> offeredServices = new ArrayList<>(); //services offered by this pro on this particular project
	private List<String> attachments = new ArrayList<>(); // a list a attachments a project has to in order to show more of his/her work.
	
	@NotEmpty(message = "project - client can not be null or empty.")
	private Client client;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project that = (Project) o;
        return Objects.equals(projectReferenceId, that.projectReferenceId) && 
        		Objects.equals(proReferenceId, that.proReferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectReferenceId, proReferenceId);
    }
}
