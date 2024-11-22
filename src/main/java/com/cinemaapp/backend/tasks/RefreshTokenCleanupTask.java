package com.cinemaapp.backend.tasks;

import com.cinemaapp.backend.repository.crud.CrudRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RefreshTokenCleanupTask {

    private final CrudRefreshTokenRepository crudRefreshTokenRepository;

    @Autowired
    public RefreshTokenCleanupTask(CrudRefreshTokenRepository crudRefreshTokenRepository) {
        this.crudRefreshTokenRepository = crudRefreshTokenRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight (job will start at 0 second, 0 minute and 0 hour every day)
    public void cleanExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        int deletedCount = crudRefreshTokenRepository.deleteByExpirationBefore(now);
        System.out.println("Deleted " + deletedCount + " expired refresh tokens.");
    }
}
