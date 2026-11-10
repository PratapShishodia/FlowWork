package com.ps.flowwork_backend.common.auditor;

import com.ps.flowwork_backend.authentication_service.util.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationUtility {

    public static String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            return "PUBLIC";
        }
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof CustomUserDetails customUserDetails) {
            username = customUserDetails.getUsers().getUserId().toString();
        } else {
            username = principal.toString(); // fallback
        }
        return username;
    }
}
