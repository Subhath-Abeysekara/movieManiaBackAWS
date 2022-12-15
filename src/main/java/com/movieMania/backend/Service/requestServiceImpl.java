package com.movieMania.backend.Service;

import com.intouncommon.backend.Repository.exception.ResourceNotFoundException;
import com.movieMania.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class requestServiceImpl implements requestService{

    @Autowired
    private com.movieMania.backend.Repository.requestRepository requestRepository;

    @Autowired
    private otherService otherService;

    @Autowired
    private com.movieMania.backend.Repository.movieRepository movieRepository;

    private String generateCode(String contact , Long id){
        String code = "";
        if (id<10){
            contact+="000";
        }
        if (id<100){
            contact+="00";
        }
        if (id<1000){
            contact+="0";
        }
        code+=contact;
        code+=id.toString();

        return code;
    }

    private request dtoToRequest(requestDto requestDto){
        request request = new request();

        request.setPayableStatus(requestDto.getPayableStatus());
        request.setContact(requestDto.getContact());
        request.setCustomerEmail(requestDto.getCustomerEmail());
        request.setCustomerName(requestDto.getCustomerName());
        request.setDriverLink(requestDto.getDriverLink());

        return request;
    }

    @Override
    public String addRequest(requestDto requestDto,String code) {

        if(code.equalsIgnoreCase("none")){

            request request = dtoToRequest(requestDto);
            Long movie = requestDto.getMovie();
            movie movie1 = movieRepository.findById(movie).orElseThrow(() ->
                    new ResourceNotFoundException("Location", "Id", movie));
            request.setMovie(movie1);
            Long id = requestRepository.save(request).getRequestId();
            String multiCode = generateCode(request.getContact(),id);
            Optional<request> request1 = requestRepository.findById(id);
            if (request1.isPresent()){
                request1.get().setCode(multiCode);
                request1.get().setMultiCode(multiCode);
                String email = request1.get().getCustomerEmail();
                requestRepository.save(request1.get());
                otherService.sendMails(email,"Your Code For Request",multiCode);
                movie1.setRate(movie1.getRate()+1);
                movieRepository.save(movie1);

            }


            return multiCode;
        }
        else {

            request request = dtoToRequest(requestDto);
            Long movie = requestDto.getMovie();
            movie movie1 = movieRepository.findById(movie).orElseThrow(() ->
                    new ResourceNotFoundException("Location", "Id", movie));
            request.setMovie(movie1);
            Long id = requestRepository.save(request).getRequestId();
            String multiCode = generateCode(request.getContact(),id);
            Optional<request> request1 = requestRepository.findById(id);
            if (request1.isPresent()){
                request1.get().setCode(multiCode);
                request1.get().setMultiCode(code);
                requestRepository.save(request1.get());
                movie1.setRate(movie1.getRate()+1);
                movieRepository.save(movie1);

            }


            return "success";
        }
    }
    @Override
    public List<requestResponse> getAllPayableRequest() {
        List<requestResponse> requestResponses = getAllRequestResponse();
        List<requestResponse> requests1 = new ArrayList<>();
        for (requestResponse requestResponse : requestResponses){
            request request = requestResponse.getRequest();
            if (request.getPayableStatus().equalsIgnoreCase("payable")){
                requests1.add(requestResponse);
            }
        }
        return requests1;
    }

    private List<requestResponse> getAllRequestResponse(){
        List<movie> movies = movieRepository.findAll();
        List<requestResponse> requestResponses = new ArrayList<>();
        for (movie movie : movies){
            List<request> requests = movie.getRequests();
            for (request request : requests){
                requestResponse requestResponse = new requestResponse();
                requestResponse.setRequest(request);
                requestResponse.setMovie(returnMovieData(movie));
                requestResponses.add(requestResponse);
            }
        }

        return requestResponses;
    }

    private movieData returnMovieData(movie movie){
        movieData movieData = new movieData();
        movieData.setAddDate(movie.getAddDate());
        movieData.setCategory(movie.getCategory());
        movieData.setImageUrl(movie.getImageUrl());
        movieData.setLaunchingImageUrl(movie.getLaunchingImageUrl());
        movieData.setMovieId(movie.getMovieId());
        movieData.setName(movie.getName());
        movieData.setRate(movie.getRate());
        movieData.setPrice(movie.getPrice());
        movieData.setStory(movie.getStory());
        movieData.setTrailerLink(movie.getTrailerLink());
        movieData.setCharacter(movie.getCharacters());

        return movieData;
    }

    @Override
    public String cancelRequest(String code) {
        List<request> requests = requestRepository.findByMultiCode(code);
        for (request request : requests){
            if (request.getAdminStatus().equalsIgnoreCase("pending")){
                requestRepository.deleteByRequestId(request.getRequestId());
                return "cancel";
            }
            return "Cant cancel now";
        }
        return "error id";
    }

    @Override
    public String addScanCopy(String url,String code) {
        List<request> requests = requestRepository.findByMultiCode(code);
        for (com.movieMania.backend.Entity.request request : requests){
            request.setSlipUrl(url);
            request.setAdminStatus("payed");
            requestRepository.save(request);
        }
        if(requests.isEmpty()){
            return "error code";
        }

        return "added";
    }

    @Override
    public List<requestResponse> getPayedRequests() {
        List<requestResponse> requestResponses = getAllRequestResponse();
        List<requestResponse> requests1 = new ArrayList<>();
        for (requestResponse requestResponse : requestResponses){
            request request = requestResponse.getRequest();
            if (request.getAdminStatus().equalsIgnoreCase("payed")&&request.getPayableStatus().equalsIgnoreCase("payable")){
                requests1.add(requestResponse);
            }
            else{
            if (request.getAdminStatus().equalsIgnoreCase("confirm")&&request.getPayableStatus().equalsIgnoreCase("payable")){
                requests1.add(requestResponse);
            }
            }
        }
        return requests1;
    }

    @Override
    public String confirmRequest(Long id) {
        Optional<request> request = requestRepository.findById(id);
        if (request.isPresent()&&request.get().getAdminStatus().equalsIgnoreCase("payed")){
            request.get().setAdminStatus("confirm");
            String email = request.get().getCustomerEmail();
            String subject = "Your Request Has Confirmed Your movie will be uploaded soon";
            otherService.sendMails(email,"Request Confirmation",subject);
            requestRepository.save(request.get());
            return "confirmed";
        }
        return "error id";
    }

    @Override
    public String rejectRequest(Long id,String reason) {
        Optional<request> request = requestRepository.findById(id);
        if (request.isPresent()) {
            if (request.get().getAdminStatus().equalsIgnoreCase("pending")) {
                String email = request.get().getCustomerEmail();
                String subject = "Your Request Has Rejected Reason - "+reason;
                otherService.sendMails(email, "Request Rejection", subject);
                requestRepository.deleteByRequestId(id);
                return "rejected";
            }
            return "cant reject";
        }

        return "error id";
    }

    @Override
    public List<requestResponse> getByCode(String code) {
        List<requestResponse> requestResponses = getAllRequestResponse();
        List<requestResponse> requestResponses1 = new ArrayList<>();
        requestResponse requestResponse1 = new requestResponse();
        for (requestResponse requestResponse : requestResponses){
            request request = requestResponse.getRequest();
            if(request.getMultiCode()!=null){
                if (request.getMultiCode().equals(code)){
                    requestResponses1.add(requestResponse);
                }
            }
        }
        return requestResponses1;
    }

    @Override
    public String sendUploadMail(String code) {
        Optional<request> request = requestRepository.findByCode(code);
        if (request.isPresent()){
            String subject = "Your Requested movie is uploaded to the driver please check your driver Link - "+request.get().getDriverLink();
            otherService.sendMails(request.get().getCustomerEmail(), "Movie Upload", subject);
            request.get().setAdminStatus("uploaded");
            requestRepository.save(request.get());
            return "sent";
        }
        return "error code";
    }

    @Override
    public List<requestResponse> getNotPayableRequests() {
        List<requestResponse> requestResponses = getAllRequestResponse();
        List<requestResponse> requests1 = new ArrayList<>();
        for (requestResponse requestResponse : requestResponses){
            request request = requestResponse.getRequest();
            if (request.getPayableStatus().equalsIgnoreCase("notPayable")&&request.getAdminShow().equalsIgnoreCase("notShow")){
                requests1.add(requestResponse);
            }
        }
        return requests1;
    }

    @Override
    public List<requestResponse> getUploadedRequests() {
        List<requestResponse> requestResponses = getAllRequestResponse();
        List<requestResponse> requests1 = new ArrayList<>();
        for (requestResponse requestResponse : requestResponses){
            request request = requestResponse.getRequest();
            if (request.getAdminStatus().equalsIgnoreCase("uploaded")&&request.getPayableStatus().equalsIgnoreCase("payable")){
                requests1.add(requestResponse);
            }
        }
        return requests1;
    }

    @Override
    public String setShowState(Long id) {
        Optional<request> request = requestRepository.findById(id);
        if (request.isPresent()&&request.get().getAdminShow().equalsIgnoreCase("notShow")){
            request.get().setAdminShow("show");
            request.get().setAdminStatus("confirm");
            requestRepository.save(request.get());
            return "saved";
        }
        return "error id";
    }
}
