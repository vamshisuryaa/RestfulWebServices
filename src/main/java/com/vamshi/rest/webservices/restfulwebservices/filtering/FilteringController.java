package com.vamshi.rest.webservices.restfulwebservices.filtering;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering", produces = "application/json")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1","value2","value3");
    }

    @GetMapping(path = "/filtering-list", produces = "application/json")
    public List<SomeBean> retrieveListOfSomeBeans(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),
        new SomeBean("value12","value22","value32"));
    }


}
