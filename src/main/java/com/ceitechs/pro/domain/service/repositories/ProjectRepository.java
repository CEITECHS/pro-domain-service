/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.Project;

/**
 * @author vctrowino
 *
 */
public interface ProjectRepository extends MongoRepository<Project, String>{
	/**
	 * 
	 * @param proReferenceId
	 * @return
	 */
	List<Project> findByProReferenceIdOrderByStartDateDesc(String proReferenceId);
	/**
	 * 
	 * @param proReferenceId
	 */
	void deleteByProReferenceId(String proReferenceId);
	/**
	 * 
	 * @param projectReferenceId
	 * @param proReferenceId
	 * @return 
	 */
	Project findByProjectReferenceIdAndProReferenceId(String projectReferenceId, String proReferenceId);
	/**
	 * 
	 * @param projectReferenceId
	 * @param proReferenceId
	 */
	void deleteByProjectReferenceIdAndProReferenceId(String projectReferenceId, String proReferenceId);

}
