package com.vamshi.rest.webservices.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonversioningController {


    //@GetMapping("v1/person")
    @GetMapping(value="/person/param", params ="version=1")
    public PersonV1 personV1(){
        return new PersonV1("Vamshi Krishna");
    }

    //@GetMapping("v2/person")
    @GetMapping(value="/person/param", params ="version=2")
    public PersonV2 personV2(){
        return new PersonV2(new Name ("Vamshi","Krishna"));
    }


    @GetMapping(value="/person/header", headers ="X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Vamshi Krishna");
    }


    @GetMapping(value="/person/header", headers ="X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name ("Vamshi","Krishna"));
    }


    @GetMapping(value="/person/produces", produces ="application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Vamshi Krishna");
    }


    @GetMapping(value="/person/produces", produces ="application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name ("Vamshi","Krishna"));
    }
    

}
