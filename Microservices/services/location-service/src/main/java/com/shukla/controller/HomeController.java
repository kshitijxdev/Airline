package com.shukla.controller;

import com.shukla.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController(){

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Hello everyone i am location service of airline microservices");

        return apiResponse;

    }
}
