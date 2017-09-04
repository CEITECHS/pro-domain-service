/**
 * 
 */
package com.ceitechs.pro.domain.service.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private static String projectReferenceId;
	private static String proReferenceId;
	private static List<Project> projects = new ArrayList<>();
	
	{
		
		
	}
	
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
	@Test
	public void testFindByProReferenceIdOrderByStartDateDesc() {
		createProjectList();
		List<Project> projects = projectRepository.findByProReferenceIdOrderByStartDateDesc(proReferenceId);
		Assert.assertTrue("The size of the list must be 3",projects.size()==3);
		Assert.assertEquals(LocalDate.now().minusYears(0), projects.get(0).getStartDate());
		Assert.assertEquals(LocalDate.now().minusYears(4), projects.get(2).getStartDate());
	}
	
	private Project createProject() {
		Project project = new Project();
		project.setEndDate(LocalDate.now());
		project.setStartDate(LocalDate.now());
		project.setProjectName("PROJECT NAME 1");
		project.setProjectReferenceId(projectReferenceId);
		project.setProReferenceId(proReferenceId);
		return project;
	}
	
	private void createProjectList() {
		for(int i=0; i<5; i++) {
			Project project = new Project();
			if(i%2==0) {
				project.setProReferenceId(proReferenceId);
			}else {
				project.setProReferenceId(ProUtility.generateIdAsString());
			}
			project.setProjectReferenceId(ProUtility.generateIdAsString());
			project.setProjectName("PROJECT NAME "+ i);
			project.setStartDate(LocalDate.now().minusYears(i));
			project.setEndDate(LocalDate.now().plusYears(i));
			projectRepository.save(project);
		}
	}
	
	@Before
	public void setUp() {
		projectReferenceId = ProUtility.generateIdAsString();
		proReferenceId = ProUtility.generateIdAsString();
		projectRepository.deleteAll();
	}
	
	@After
	public void tearDown() {
	}

}
