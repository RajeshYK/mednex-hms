package com.mednex.hms.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mednex.hms.tenant.TenantContext;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
    private  JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

    	// 1️⃣ Set tenant into context (IMPORTANT for schema-per-tenant)
        TenantContext.setTenant(request.getTenant());

        // 2️⃣ Generate JWT with role + tenant claims
        String token = jwtService.generateToken(
                request.getRole(),
                request.getTenant()
        );

        // 3️⃣ Return response
        return new LoginResponse(
                token,
                request.getRole(),
                "Login successful"
        );
    }
}