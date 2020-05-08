package com.vamshi.rest.webservices.restfulwebservices.helloworld;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/start")
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    //@RequestMapping(method= RequestMethod.GET,path="/hello-world")

    public String helloWorld()
    {
        return"Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable("name") String name)//This bean is automatically converted to JSON and return back
    {
        return new HelloWorldBean(String.format("Hello World,%s",name));
    }

    @GetMapping("/hello-world-i18n")
    //@RequestMapping(method= RequestMethod.GET,path="/hello-world")

    public String helloWorldInternationalized(/*@RequestHeader(name = "Accept-Language",  required=false) Locale locale*/)//Locale comes as part of the request header
    {

        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
        //return"Good Morning";
    }










}
