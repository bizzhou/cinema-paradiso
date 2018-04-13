//package com.paridiso.cinema.entity;
//
//import javax.persistence.*;
//
//// each user has a list of UserRatings
//// todo
//@Entity
//@Table(name = "UserRatings")
//public class UserRating {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer userId;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Film film;
//
//    private Double userRating;
//
//    public UserRating() {
//    }
//
//
//
//}
