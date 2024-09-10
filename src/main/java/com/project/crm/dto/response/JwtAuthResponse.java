package com.project.crm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	private String userUuid; // UserId alanı eklendi

}