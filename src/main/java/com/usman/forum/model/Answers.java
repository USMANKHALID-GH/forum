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
@Table(name = "answer_tbl")
public class Answers  extends AbstractModel{

    private static final long serialVersionUID=1L;
//    naming
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
    private Questions question;
    @OneToMany(mappedBy = "answer",cascade = CascadeType.REMOVE)
    private List<SubAnswers> subAnswers;
    private  boolean bestAnswer;


    @Column(columnDefinition = "integer default 0")
    private  Integer likeCount= 0;





}
