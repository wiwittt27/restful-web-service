package com.alawiyaa.rest.webservice.restful_web_services.usecase;

import lombok.Data;

@Data
public class HellowWorldBean {

    private String message;
 
    public HellowWorldBean(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "hwlloWorldBean [message=" + message +"]";
    }

}
