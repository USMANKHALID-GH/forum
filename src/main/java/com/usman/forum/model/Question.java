package com.usman.forum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Question extends AbstractModel {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    private String questionTitle;
    @NotBlank
    @NotNull(message = "what your question")
    private String questions;
    private String image;

    @ManyToOne
    @Column(nullable = false)
    private User user;
}
