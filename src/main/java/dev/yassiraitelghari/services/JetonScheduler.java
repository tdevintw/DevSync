package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.domain.User;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.List;

@Singleton
@Startup
public class JetonScheduler {

    private UserService userService = new UserServiceImp();
    private RequestService requestService = new RequestServiceImp();

    @Schedule(dayOfMonth = "1", hour = "0", minute = "0", second = "0", persistent = true)
    public void addMonthlyJeton() {
        userService.updateDeleteToken();
    }

    @Schedule(hour = "0", minute = "0", persistent = true)
    public void addDailyJeton() {
        userService.updateReplaceToken();
    }

    @Schedule(hour = "6", minute = "*", persistent = true)
    public void extraJetons() {
        List<Request> requests = requestService.pendingRequests();
        List<User> usersToAddJetons = requests.stream().map(request -> request.getTask().getUser()).distinct().toList();
        for (User user : usersToAddJetons) {
            user.setReplaceJeton(4);
            userService.update(user);
        }
        requestService.updateRequestsStatusToNotResponded(requests);
    }
}