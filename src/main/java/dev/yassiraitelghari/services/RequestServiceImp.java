package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.repositories.RequestRepository;
import dev.yassiraitelghari.repositories.RequestRepositoryImp;

public class RequestServiceImp implements RequestService {
    private RequestRepository requestRepository = new RequestRepositoryImp();

    @Override
    public Request add(Request request) {
        return requestRepository.add(request);
    }

}
