package com.alawiyaa.rest.webservice.restful_web_services.delivery;

import org.springframework.web.bind.annotation.RestController;

import com.alawiyaa.rest.webservice.restful_web_services.model.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue getFilter() {
        SomeBean someBean = new SomeBean("value 1", "value 2", "value 3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filteringList")
    public MappingJacksonValue getFilterList() {
        List<SomeBean> asList = Arrays.asList(new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 4", "value 5", "value 6"));
                
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(asList);
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1","fie1ld3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

}
