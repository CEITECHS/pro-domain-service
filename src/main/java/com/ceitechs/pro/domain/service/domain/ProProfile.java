/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vctrowino
 *
 */
@Getter
@Setter
public class ProProfile {
	private String emailAddress;
	private String password;
	private LocalDateTime lastPasswordChangeDate;
	private String verificationCode;
	private LocalDate verificationDate;
	private boolean isVerified;
	private LocalDate createdDate = LocalDate.now();
}
