package com.cinemaapp.backend.tasks;

import com.cinemaapp.backend.repository.crud.CrudPasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PasswordResetCleanupTask {

    private final CrudPasswordResetRepository crudPasswordResetRepository;

    @Autowired
    public PasswordResetCleanupTask(CrudPasswordResetRepository crudPasswordResetRepository) {
        this.crudPasswordResetRepository = crudPasswordResetRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanExpiredResetCodes() {
        int deletedCodes = crudPasswordResetRepository.deleteByExpirationTimeBefore(LocalDateTime.now());
        System.out.println("Deleted " + deletedCodes + " expired password reset codes.");
    }

}
