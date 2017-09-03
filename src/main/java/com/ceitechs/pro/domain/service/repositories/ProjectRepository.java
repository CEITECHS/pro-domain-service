/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.Project;

/**
 * @author vctrowino
 *
 */
public interface ProjectRepository extends MongoRepository<Project, String>{

}
