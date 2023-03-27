package com.usman.forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Slf4j
public abstract class AbstractModel implements Serializable {
    private static final long serialVersionUID=1L;

    @CreatedDate
    @JsonIgnore
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;


    @PrePersist
    private void perPersist(){

        this.createdDate=LocalDateTime.now();
        log.info(this.createdDate+".......................");
    }
}
