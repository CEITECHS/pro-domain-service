/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.Material;

/**
 * @author vctrowino
 *
 */
public interface MaterialRepository extends MongoRepository<Material, String>{

}
