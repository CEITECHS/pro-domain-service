/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
public class Client {
	private String clientReferenceId;
	private String firstName; // client or project supervisor firstName
	private String lastName; // client or project supervisor lastName
	private String phoneNumber;
	private String emailAddress;
	private String companyName; // if a client is a compoany.
	private Project project;

}
