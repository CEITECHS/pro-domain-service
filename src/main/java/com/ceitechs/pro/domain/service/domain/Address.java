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
public class Address {
	private double longitude; // Longitude of an address (location)
	private double latitude; // Latitude of an address (location)
	private String addressLine1; // Street name of an address.
	private String addressLine2; // house number of an address.
	private String district; // District name of an address.
	private String region; // Region name of an address.
	private String country; // Country name of an address.
}
