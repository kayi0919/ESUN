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
@Table(name = "member" )
public class User {
    @Id
    @Column(name="User_ID")
    private String userId;

    @Column(name="User_Name")
    private String username;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="Biography")
    private String biography;

    @Column(name="enabled")
    private String enabled;


    @Transient
    private String action;
}