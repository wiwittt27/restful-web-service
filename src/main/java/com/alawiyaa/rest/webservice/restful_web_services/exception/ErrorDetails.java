package com.alawiyaa.rest.webservice.restful_web_services.exception;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String detail;

    public ErrorDetails(LocalDate timestamp, String message, String detail) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
