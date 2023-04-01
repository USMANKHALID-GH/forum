package com.usman.forum.model;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Likes   {
  private  Long likeCount;
}
