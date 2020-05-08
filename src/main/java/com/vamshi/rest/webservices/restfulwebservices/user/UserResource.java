package com.vamshi.rest.webservices.restfulwebservices.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserResource {


    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users", produces = "application/json")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }


    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){

        User user = service.findOne(id);
        if(user==null)
        {
            throw new UserNotFoundException("id-"+id);
        }

        Resource<User> resource = new Resource<User>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;


        //EntityModel<User> model = new EntityModel<>(user);

       // WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        //model.add(linkTo.withRel("all-users"));

        //return model;

        //return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody  User user){ // The input is of a JSON structure and tha input is mapped to the User object
        User savedUser =  service.save(user);

       URI location  =   ServletUriComponentsBuilder.
                       fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();


         return ResponseEntity.created(location).build();
            //ResponseEntity.created(location) is used to return a created status


    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){

        User user = service.deleteById(id);
        if(user==null)
        {
            throw new UserNotFoundException("id-"+id);
        }

    }





}
