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
	private String phoneNumber;
	private boolean isPrimary; //Most prefered to be contacted with.
	/**
	 * method you want to be contacted using this phone number. Either by text or call.
	 * text is selected by default if you don't choose.
	 */
	private String contactMethod = contactMethodType.TEXT.toString(); 
	public enum contactMethodType {
		TEXT,
		CALL
	}
}
