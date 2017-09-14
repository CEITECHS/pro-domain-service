/**
 * 
 */
package com.ceitechs.pro.domain.service.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Transient;

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
	
	@Transient
    private String verificationPathParam; //temporarily holds verification token

	private boolean isVerified;
	private LocalDate createdDate = LocalDate.now();
}
