package com.tumelya.autorization_user.config.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ShutdownScheduler {

    @Value("${scheduled.shutdown-time}")
    private String shutdownTime;

    @Scheduled(fixedRate = 600000) // Каждые 10 минут
    public void checkShutdownTime() {
        LocalDateTime shutdownDateTime = LocalDateTime.parse(shutdownTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (LocalDateTime.now().isAfter(shutdownDateTime)) {
            System.exit(0);  // Завершение работы приложения
        }
    }
}
