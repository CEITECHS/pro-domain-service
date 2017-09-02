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
import com.ceitechs.pro.domain.service.domain.Material;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class MaterialRepositoryTest extends AbstractProDomainServiceIntegrationTest{
	@Autowired
	private MaterialRepository materialRepository;
	private static String createdBy;
	private static String materialReferenceId;
	
	@Test
	public void testSave() {
		Material material = createMaterial();
		materialRepository.save(material);
		Material savedMaterial = materialRepository.findOne(material.getMaterialReferenceId());
		Assert.assertEquals(material, savedMaterial);
	}
	
	@Test
	public void testUpdate() {
		Material material = createMaterial();
		materialRepository.save(material);
		Material savedMaterial = materialRepository.findOne(material.getMaterialReferenceId());
		
		savedMaterial.setDescription("DESCRIPTION TWO");
		savedMaterial.setMaterialName("NAME UPDATED");
		
		materialRepository.save(savedMaterial);
		Material updatedMaterial = materialRepository.findOne(savedMaterial.getMaterialReferenceId());
		Assert.assertEquals(savedMaterial, updatedMaterial);
		
	}
	
	@Test
	public void testDelete() {
		Material material = createMaterial();
		materialRepository.save(material);
		Material savedMaterial = materialRepository.findOne(material.getMaterialReferenceId());
		
		Assert.assertEquals(material, savedMaterial);
		materialRepository.delete(savedMaterial);
		Material deletedMaterial = materialRepository.findOne(savedMaterial.getMaterialReferenceId());
		Assert.assertEquals(null, deletedMaterial);
	}
	
	private Material createMaterial() {
		Material material = new Material();
		material.setCreatedBy(createdBy);
		material.setDescription("DESCRIPTION ONE");
		material.setMaterialName("NAME 1");
		material.setMaterialReferenceId(materialReferenceId);
		return material;
	}
	@Before
	public void setUp() {
		createdBy = ProUtility.generateIdAsString();
		materialReferenceId = ProUtility.generateIdAsString();
		materialRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}
}
