/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceitechs.pro.domain.service.AbstractProDomainServiceIntegrationTest;
import com.ceitechs.pro.domain.service.domain.Project;
import com.ceitechs.pro.domain.service.util.ProUtility;

/**
 * @author vctrowino
 *
 */
public class ProjectRepositoryTest extends AbstractProDomainServiceIntegrationTest {
	@Autowired
	private ProjectRepository projectRepository;
	private static String phaseReferenceId;
	
	@Test
	public void testSave() {
		Project project = createProject();
		projectRepository.save(project);
		Project savedProject = projectRepository.findOne(project.getProjectReferenceId());
		Assert.assertEquals(project, savedProject);
	}
	
	@Test
	public void testUpdate() {
		Project project = createProject();
		projectRepository.save(project);
		Project savedProject = projectRepository.findOne(project.getProjectReferenceId());
		
		savedProject.setProjectName("PROJECT NAME UPDATED");
		
		projectRepository.save(savedProject);
		Project updatedProject = projectRepository.findOne(savedProject.getProjectReferenceId());
		Assert.assertEquals(savedProject, updatedProject);
		
	}
	
	@Test
	public void testDelete() {
		Project project = createProject();
		projectRepository.save(project);
		Project savedProject = projectRepository.findOne(project.getProjectReferenceId());
		
		Assert.assertEquals(project, savedProject);
		projectRepository.delete(savedProject);
		Project deletedProject = projectRepository.findOne(savedProject.getProjectReferenceId());
		Assert.assertEquals(null, deletedProject);
	}
	
	private Project createProject() {
		Project project = new Project();
		project.setEndDate(LocalDate.now());
		project.setStartDate(LocalDate.now());
		project.setProjectName("PROJECT NAME 1");
		project.setProjectReferenceId(phaseReferenceId);
		return project;
	}
	
	@Before
	public void setUp() {
		phaseReferenceId = ProUtility.generateIdAsString();
		projectRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}

}
