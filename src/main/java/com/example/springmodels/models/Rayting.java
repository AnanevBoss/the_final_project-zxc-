package com.example.springmodels.models;

import javax.persistence.*;

@Entity
@Table(name = "rayting_user")
public class Rayting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rayting_user_id")
     private Long rayting_user_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
     private User user;
    @Column(name = "rayting")
     private String rayting;
     public Rayting (){

     }

    public Long getRayting_user_id() {
        return rayting_user_id;
    }

    public void setRayting_user_id(Long rayting_user_id) {
        this.rayting_user_id = rayting_user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRayting() {
        return rayting;
    }

    public void setRayting(String rayting) {
        this.rayting = rayting;
    }
}
