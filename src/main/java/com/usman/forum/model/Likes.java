package com.usman.forum.model;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Data

public class Likes  extends AbstractModel {
  private static final long serialVersionUID=1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Long id;




  @Column(name="user_id")
  private Long user;
  @Column(name = "answer_id")
  private Long answer;


}
