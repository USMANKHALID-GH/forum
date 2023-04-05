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
public class Question extends AbstractModel {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String title;
    @NotBlank
    @NotNull
    private String content;
    private String image;

    @Column(columnDefinition = "boolean default false")
    private boolean isAnswered;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)

    private List<Answer> answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catergory_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "question")
    private List<QuestionLike> questionLike;

}
