package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import org.springframework.web.bind.annotation.RestController;

import com.alawiyaa.rest.webservice.restful_web_services.model.Name;
import com.alawiyaa.rest.webservice.restful_web_services.model.PersonV1;
import com.alawiyaa.rest.webservice.restful_web_services.model.PersonV2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class VersioningPersonController {
    // URL versioning
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("bob", "Charlie"));
    }

    // Param versioning
    @GetMapping(path = "/person", params = "version={version}")
    public PersonV1 getSecondVersionOfPersonRequestParameter(@RequestParam String version) {
        return new PersonV1("Imam Bob");
    }

    @GetMapping(path = "/persona", params = "version=2")
    public PersonV2 getSecondVersionOfPersonReqPersonV2() {
        return new PersonV2(new Name("bob", "Charlie"));
    }
    //Custom Header versioning
        @GetMapping(path = "/person/header", headers  = "X-API-VERSION=1")
    public PersonV1 getSecondVersionOfPersonHeaders1() {
        return new PersonV1("Imam Bob");
    }

    @GetMapping(path = "/person/header", headers  = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeaders2() {
        return new PersonV2(new Name("bob", "Charlie"));
    }
}
