package dev.yassiraitelghari.services.implmentations;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.repositories.interfaces.RequestRepository;
import dev.yassiraitelghari.repositories.implmentations.RequestRepositoryImp;
import dev.yassiraitelghari.services.interfaces.RequestService;

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

    @Override
    public Request update(Request request){
        return requestRepository.update(request);
    }

    @Override
    public List<Request> pendingRequests(){
        return  requestRepository.pendingRequests();
    }

    @Override
    public List<Request> updateRequestsStatusToNotResponded(List<Request> requests){
        requests.stream().forEach(request -> {
            request.setStatus("Not Responded");
            this.update(request);
        });
        return requests;
    }

    @Override
    public List<Request> RequestWithToken(){
     return requestRepository.RequestWithToken() ;
    }
}
