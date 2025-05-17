package com.alawiyaa.rest.webservice.restful_web_services.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    
    private String field1;
    // @JsonIgnore
    private String field2;

    private String field3;

}
