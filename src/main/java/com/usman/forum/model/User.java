package com.usman.forum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractModel{

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String firstName;
    private String LastName;
    private String relatedField;
    @NotBlank
    @Email(message = "email must be provided")
    @Column(unique = true ,nullable = false)
    private  String email;
    @NotBlank
    @Column(unique = true,nullable = false)
    private String phoneNumber;


////    naming ? relations
//    @OneToOne(mappedBy = "user")
//    private Comments comments;
//
//    @OneToMany(mappedBy = "user")
//    private List<Question> questions;
}
