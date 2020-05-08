package com.vamshi.rest.webservices.restfulwebservices.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController2 {


    //Field1, Field2
    @GetMapping(path = "/filtering2", produces = "application/json")
    public MappingJacksonValue retrieveSomeBean(){

       SomeBean2 someBean2 = new SomeBean2("value1","value2","value3");


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("someBean2Filter",filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean2);

        mappingJacksonValue.setFilters(filters);

        return  mappingJacksonValue;
    }

    //Field2, Field3
    @GetMapping(path = "/filtering-list2", produces = "application/json")
    public MappingJacksonValue retrieveListOfSomeBeans(){

        List<SomeBean2> list = Arrays.asList(new SomeBean2("value1","value2","value3"),
                new SomeBean2("value12","value22","value32"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("someBean2Filter",filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        mappingJacksonValue.setFilters(filters);


        return mappingJacksonValue;
    }


}
