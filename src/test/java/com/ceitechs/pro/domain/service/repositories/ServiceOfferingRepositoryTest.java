/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.ServiceOffering;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class ServiceOfferingRepositoryTest extends AbstractProDomainServiceIntegrationTest {
	@Autowired
	private ServiceOfferingRepository serviceOfferingRepository;
	private static String serviceReferenceId;
	private static String proReferenceId;
	private static String subPhaseReferenceId;
	
	@Test
	public void testSave() {
		ServiceOffering service = createService();
		serviceOfferingRepository.save(service);
		ServiceOffering savedService = serviceOfferingRepository.findOne(service.getServiceReferenceId());
		Assert.assertEquals(service, savedService);
	}
	
	@Test
	public void testUpdate() {
		ServiceOffering service = createService();
		serviceOfferingRepository.save(service);
		ServiceOffering savedService = serviceOfferingRepository.findOne(service.getServiceReferenceId());
		
		savedService.setServiceDescription("SERVICE DESCRIPTION UPDATED");
		
		serviceOfferingRepository.save(savedService);
		ServiceOffering updatedService = serviceOfferingRepository.findOne(savedService.getServiceReferenceId());
		Assert.assertEquals(savedService, updatedService);
		
	}
	
	@Test
	public void testDelete() {
		ServiceOffering service = createService();
		serviceOfferingRepository.save(service);
		ServiceOffering savedService = serviceOfferingRepository.findOne(service.getServiceReferenceId());
		
		Assert.assertEquals(service, savedService);
		serviceOfferingRepository.delete(savedService);
		ServiceOffering deletedService = serviceOfferingRepository.findOne(savedService.getServiceReferenceId());
		Assert.assertEquals(null, deletedService);
	}
	
	@Test
	public void testFindByProReferenceId() {
		ServiceOffering service = createService();
		serviceOfferingRepository.save(service);
		List<ServiceOffering> savedServices = serviceOfferingRepository.findByProReferenceId(service.getProReferenceId());
		Assert.assertTrue("List size must be greater than zero.", savedServices.size()>0);
		Assert.assertEquals(service.getProReferenceId(), savedServices.get(0).getProReferenceId());
		Assert.assertEquals(service, savedServices.get(0));
	}
	
	private ServiceOffering createService() {
		ServiceOffering service = new ServiceOffering();
		service.setProReferenceId(proReferenceId);
		service.setServiceReferenceId(serviceReferenceId);
		service.setServiceDescription("SERVICE DESCRIPTION");
		service.setSubPhaseReferenceId(subPhaseReferenceId);
		return service;
	}
	
	@Before
	public void setUp() {
		serviceReferenceId = ProUtility.generateIdAsString();
		proReferenceId = ProUtility.generateIdAsString();
		subPhaseReferenceId = ProUtility.generateIdAsString();
		serviceOfferingRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}

}
