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
@Getter
@Setter
@ToString
public class Client {
	private String firstName; // client or project supervisor firstName
	private String lastName; // client or project supervisor lastName
	private String phoneNumber;
	private String emailAddress;
	private Company company; // if a client is a company.
}
