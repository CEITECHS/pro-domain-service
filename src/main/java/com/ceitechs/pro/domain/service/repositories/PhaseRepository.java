/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.Phase;

/**
 * @author vctrowino
 *
 */
public interface PhaseRepository extends MongoRepository<Phase, String>{

}
