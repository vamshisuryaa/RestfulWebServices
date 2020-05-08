package com.vamshi.rest.webservices.restfulwebservices.user;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;
                                         // By default it will directly try to fetch the user
    @ManyToOne(fetch = FetchType.LAZY) // Unless called explicitly doesn't fetch the details  otherwise could cause recursion user calling posts and posts calling user
    @JsonIgnore
    private User user;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //*** IMP --> Dont print User as user tries to print posts and vice versa and we end up out of memory
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
