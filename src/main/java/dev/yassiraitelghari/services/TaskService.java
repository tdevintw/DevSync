package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;

import java.time.LocalDateTime;

public interface TaskService {
    boolean validateTitle(String title);

    boolean validateDescription(String description);

    boolean validateStartDate(LocalDateTime startDate);

    boolean validateDateLimit(LocalDateTime dateLimit , LocalDateTime startDate);

    boolean isDateLimitWithin3Days(LocalDateTime dateTime);

    Task add(Task tak);
}
