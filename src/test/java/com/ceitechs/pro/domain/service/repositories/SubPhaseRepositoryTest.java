/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.SubPhase;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class SubPhaseRepositoryTest extends AbstractProDomainServiceIntegrationTest {
	@Autowired
	private SubPhaseRepository subPhaseRepository;
	private static String subPhaseReferenceId;
	private static String phaseReferenceId;
	private static String proReferenceId;
	
	@Test
	public void testSave() {
		SubPhase subPhase = createSubPhase();
		subPhaseRepository.save(subPhase);
		SubPhase savedSubPhase = subPhaseRepository.findOne(subPhase.getSubPhaseReferenceId());
		Assert.assertEquals(subPhase, savedSubPhase);
	}
	
	@Test
	public void testUpdate() {
		SubPhase subPhase = createSubPhase();
		subPhaseRepository.save(subPhase);
		SubPhase savedSubPhase = subPhaseRepository.findOne(subPhase.getSubPhaseReferenceId());
		
		savedSubPhase.setSubPhaseName("SUBPHASE NAME UPDATED");
		savedSubPhase.setDescription("SUBPHASE DESCRIPTION");
		
		subPhaseRepository.save(savedSubPhase);
		SubPhase updatedSubPhase = subPhaseRepository.findOne(savedSubPhase.getSubPhaseReferenceId());
		Assert.assertEquals(savedSubPhase, updatedSubPhase);
		
	}
	
	@Test
	public void testDelete() {
		SubPhase subPhase = createSubPhase();
		subPhaseRepository.save(subPhase);
		SubPhase savedSubPhase = subPhaseRepository.findOne(subPhase.getSubPhaseReferenceId());
		
		Assert.assertEquals(subPhase, savedSubPhase);
		subPhaseRepository.delete(savedSubPhase);
		SubPhase deletedSubPhase = subPhaseRepository.findOne(savedSubPhase.getSubPhaseReferenceId());
		Assert.assertEquals(null, deletedSubPhase);
	}
	
	private SubPhase createSubPhase() {
		SubPhase subPhase = new SubPhase();
		subPhase.setCreatedBy(proReferenceId);
		subPhase.setSubPhaseName("SUBPHASE NAME");
		subPhase.setDescription("SUBPHASE DESCRIPTION");
		subPhase.setSubPhaseReferenceId(subPhaseReferenceId);
		subPhase.setPhaseReferenceId(phaseReferenceId);
		return subPhase;
	}
	
	@Before
	public void setUp() {
		subPhaseReferenceId = ProUtility.generateIdAsString();
		phaseReferenceId = ProUtility.generateIdAsString();
		proReferenceId = ProUtility.generateIdAsString();
		subPhaseRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}

}
