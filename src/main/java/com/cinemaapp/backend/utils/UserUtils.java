package com.cinemaapp.backend.utils;

import com.cinemaapp.backend.config.security.CustomUserDetails;
import com.cinemaapp.backend.service.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    // get current user from context
    public static User getCurrentUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getUser();
    }
}
