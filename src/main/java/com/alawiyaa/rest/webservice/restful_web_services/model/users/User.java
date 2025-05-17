package com.alawiyaa.rest.webservice.restful_web_services.model.users;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @JsonIgnore
    private Integer id;
    @Size(min = 2,message = "Name Should have atleast 2 character")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "BirthDate Should be in the Past")
    private LocalDate birthDate;
}
