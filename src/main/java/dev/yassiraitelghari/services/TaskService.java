package dev.yassiraitelghari.services;

import java.time.LocalDateTime;

public interface TaskService {
    boolean validateTitle(String title);

    boolean validateDescription(String description);

    boolean validateStartDate(LocalDateTime startDate);

    boolean validateDateLimit(LocalDateTime dateLimit);
}
