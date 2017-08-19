/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vctrowino
 *
 */
@ToString
@Getter
@Setter
public class Phone {
	private boolean isPrimary; //Most prefered to be contacted with.
	private String contactMethod; //method you want to be contacted using this phone number. Either by text or call.
	public enum contactMethodType {
		TEXT,
		CALL
	}
}
