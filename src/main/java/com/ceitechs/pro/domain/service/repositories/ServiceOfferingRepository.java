/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.ServiceOffering;

/**
 * @author vctrowino
 *
 */
public interface ServiceOfferingRepository extends MongoRepository<ServiceOffering, String>{
	/**
	 * 
	 * @param proReferenceId
	 * @return
	 */
	List<ServiceOffering> findByProReferenceId(String proReferenceId);

}
