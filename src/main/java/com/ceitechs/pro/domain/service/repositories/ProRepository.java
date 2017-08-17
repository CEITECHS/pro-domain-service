/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ceitechs.pro.domain.service.domain.Pro;

/**
 * @author vctrowino
 *
 */
public interface ProRepository extends MongoRepository<Pro, String> {
	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	Pro findByProProfileEmailAddressIgnoreCaseAndProProfileIsVerifiedTrue(String emailAddress);
	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	Pro findByProProfileEmailAddressIgnoreCase(String emailAddress);
	/**
	 * 
	 * @param proReferenceId
	 * @return
	 */
	Pro findByProReferenceIdIgnoreCaseAndProProfileIsVerifiedTrue(String proReferenceId);
	/**
	 * 
	 * @param proReferenceId
	 * @return
	 */
	Pro findByProReferenceIdIgnoreCase(String proReferenceId);
}
