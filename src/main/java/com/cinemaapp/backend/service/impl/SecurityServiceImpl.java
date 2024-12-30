package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.SecurityService;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.utils.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public User getCurrentUser() {
        return UserUtils.getCurrentUser();
    }
}
