package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Request;

import java.util.List;

public interface RequestRepository{
    Request add(Request request);

    List<Request> getAll();

    Request get(int requestId);



}
