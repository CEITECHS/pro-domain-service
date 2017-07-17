/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
public class SubPhase {
	private String subPhaseReferenceId;
	private String subPhaseName;
	private String description;
	private Phase phase;
	private List<Material> materials = new ArrayList<>();

}
