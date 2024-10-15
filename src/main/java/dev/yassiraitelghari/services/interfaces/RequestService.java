package dev.yassiraitelghari.services.interfaces;

import dev.yassiraitelghari.domain.Request;

import java.util.List;

public interface RequestService {
    Request add(Request request);

    List<Request> getAll();

    Request get(int requestId);

    Request update(Request request);

    List<Request> pendingRequests();

    List<Request> updateRequestsStatusToNotResponded(List<Request> requests);

    List<Request> RequestWithToken();

}
