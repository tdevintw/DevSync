package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.repositories.RequestRepository;
import dev.yassiraitelghari.repositories.RequestRepositoryImp;

import java.util.List;

public class RequestServiceImp implements RequestService {
    private RequestRepository requestRepository = new RequestRepositoryImp();

    @Override
    public Request add(Request request) {
        return requestRepository.add(request);
    }

    @Override
    public List<Request> getAll(){
        return requestRepository.getAll();
    }

    @Override
    public Request get(int requestId){
        return requestRepository.get(requestId);
    }



}
