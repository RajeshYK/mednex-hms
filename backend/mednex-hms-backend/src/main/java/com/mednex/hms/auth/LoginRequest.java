package com.mednex.hms.auth;

import lombok.Data;

@Data
public class LoginRequest {
	private String tenant;
    private String role;
}
