package dev.yassiraitelghari.services;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class JetonScheduler {

    private UserService userService = new UserServiceImp();

    @Schedule(dayOfMonth = "1", hour = "0", minute = "0", second = "0", persistent = true)
    public void addMonthlyJeton() {
        userService.updateDeleteToken();
    }

    @Schedule(hour = "0", minute = "0" ,persistent = true)
    public void addDailyJeton() {
        userService.updateReplaceToken();
    }
}