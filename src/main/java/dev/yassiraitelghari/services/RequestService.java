package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Request;

import java.util.List;

public interface RequestService {
    Request add(Request request);

    List<Request> getAll();

    Request get(int requestId);
}
