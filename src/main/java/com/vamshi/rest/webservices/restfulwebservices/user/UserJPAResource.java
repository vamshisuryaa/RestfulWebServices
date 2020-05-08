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
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {



    @Autowired
   private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

  /*  @Autowired
    private UserDaoService service;*/

    @GetMapping(path = "/jpa/users", produces = "application/json")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){

        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }

        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;


        //EntityModel<User> model = new EntityModel<>(user);

       // WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        //model.add(linkTo.withRel("all-users"));

        //return model;

        //return user;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody  User user){ // The input is of a JSON structure and tha input is mapped to the User object
        User savedUser =  userRepository.save(user);

       URI location  =   ServletUriComponentsBuilder.
                       fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();


         return ResponseEntity.created(location).build();
            //ResponseEntity.created(location) is used to return a created status


    }


    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){

         userRepository.deleteById(id);// Note that delete method in repository doesn't return anything

    }


    @GetMapping(path = "/jpa/users/{id}/posts", produces = "application/json")
    public List<Post> retrieveAllPosts(@PathVariable int id){

        Optional<User> userOptional =  userRepository.findById(id);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }

        return  userOptional.get().getPosts();


    }


    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post){

        Optional<User> userOptional =  userRepository.findById(id);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }

            User user = userOptional.get();

            post.setUser(user);

            postRepository.save(post);

        URI location  =   ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(post.getId()).
                toUri();


        return ResponseEntity.created(location).build();



    }







}
