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
import com.ceitechs.pro.domain.service.domain.Phase;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class PhaseRepositoryTest extends AbstractProDomainServiceIntegrationTest{
	@Autowired
	private PhaseRepository phaseRepository;
	private static String phaseReferenceId;
	private static String createdBy;
	
	@Test
	public void testSave() {
		Phase phase = createPhase();
		phaseRepository.save(phase);
		Phase savedPhase = phaseRepository.findOne(phase.getPhaseReferenceId());
		Assert.assertEquals(phase, savedPhase);
	}
	
	@Test
	public void testUpdate() {
		Phase phase = createPhase();
		phaseRepository.save(phase);
		Phase savedPhase = phaseRepository.findOne(phase.getPhaseReferenceId());
		
		savedPhase.setDescription("DESCRIPTION TWO");
		savedPhase.setPhaseName("NAME UPDATED");
		
		phaseRepository.save(savedPhase);
		Phase updatedPhase = phaseRepository.findOne(savedPhase.getPhaseReferenceId());
		Assert.assertEquals(savedPhase, updatedPhase);
		
	}
	
	@Test
	public void testDelete() {
		Phase phase = createPhase();
		phaseRepository.save(phase);
		Phase savedPhase = phaseRepository.findOne(phase.getPhaseReferenceId());
		
		Assert.assertEquals(phase, savedPhase);
		phaseRepository.delete(savedPhase);
		Phase deletedPhase = phaseRepository.findOne(savedPhase.getPhaseReferenceId());
		Assert.assertEquals(null, deletedPhase);
	}
	
	private Phase createPhase() {
		Phase phase = new Phase();
		phase.setCreatedBy(createdBy);
		phase.setDescription("DESCRIPTION ONE");
		phase.setPhaseName("NAME 1");
		phase.setPhaseReferenceId(phaseReferenceId);
		return phase;
	}
	
	@Before
	public void setUp() {
		createdBy = ProUtility.generateIdAsString();
		phaseReferenceId = ProUtility.generateIdAsString();
		phaseRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}

}
