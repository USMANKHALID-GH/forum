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

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class Answer extends AbstractModel{

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    private User user;
    @NotBlank
    @NotNull(message = "write your answers")
    private String content;
    private String image;
    @ManyToOne
    @JoinColumn(name = "question_id" ,nullable = false)
    private Question question;
    @OneToMany(mappedBy = "answer",cascade = CascadeType.REMOVE)
    private List<SubAnswer> subAnswer;
    private  boolean bestAnswer;
    @OneToMany(mappedBy = "answer")
    private List<AnswerLike> answerLike;









}
