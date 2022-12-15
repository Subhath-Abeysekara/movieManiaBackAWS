package com.movieMania.backend.Service;

import com.movieMania.backend.Entity.requestDto;
import com.movieMania.backend.Entity.requestDto;
import com.movieMania.backend.Entity.requestResponse;

import java.util.List;

public interface requestService {

    String addRequest(requestDto requestDto , String code);
    List<requestResponse> getAllPayableRequest();
    String cancelRequest(String code);
    String addScanCopy(String url,String code);
    List<requestResponse> getPayedRequests();
    String confirmRequest(Long id);
    String rejectRequest(Long id,String reason);
    List<requestResponse> getByCode(String code);
    String sendUploadMail(String code);
    List<requestResponse> getNotPayableRequests();
    List<requestResponse> getUploadedRequests();
    String setShowState(Long id);
}
