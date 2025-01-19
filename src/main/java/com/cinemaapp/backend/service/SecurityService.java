package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.User;

public interface SecurityService {

    User getCurrentUser();
}
