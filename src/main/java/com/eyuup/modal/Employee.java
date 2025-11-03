package com.eyuup.modal;

import java.time.LocalDateTime;

import com.eyuup.domain.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @ManyToOne
    private Branch branch;
    private UserRole position;
    private Double salary;
    private  Boolean status ;
    private  LocalDateTime createdAt ;
    private  LocalDateTime updatedAt ;


    @PrePersist
    protected void createdAt(){
        createdAt=LocalDateTime.now();
    }
    
    @PreUpdate
    protected void updatedAt(){
        updatedAt=LocalDateTime.now();
    }

}
