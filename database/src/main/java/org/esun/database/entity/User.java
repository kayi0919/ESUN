package org.esun.database.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "User" )
@Table(name = "member" )
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="User_ID")
//    private Long user_id;
//
//    @Column(name="User_Name")
//    private String user_name;
//
//    @Column(name="Email")
//    private String email;
//
//    @Column(name="Password")
//    private String password;
//
//    @Column(name="Biography")
//    private String biography;
//
//    @Column(name="enabled")
//    private String enabled;


    @Id
    @Column(name="username")
    private String username;

    @Column(name="realname")
    private String realname;

    @Column(name="password")
    @JsonIgnore
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="enabled")
    private String enabled;

    @Transient
    private String action;
}
