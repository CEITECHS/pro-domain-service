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
public class Phase {
	private String phaseReferenceId;
	private String phaseName;
	private String description;
	private List<SubPhase> subPhases = new ArrayList<>();
}
