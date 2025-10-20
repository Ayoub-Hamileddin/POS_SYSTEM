package com.eyuup.modal;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name ;

    private String address ;

    @Column(unique = true)
    private String phone ;


    @Column(unique = true)
    @Email
    private String email ;

    // @ElementCollection:  it create a small table of workings day with the foreign Key branch_id ;
    @ElementCollection
    private List<String> workingsDays;

    private LocalTime openTime;
    private LocalTime closeTime;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @ManyToOne
    private Store store;

    @OneToOne
    private User manager;


    @PrePersist
    protected void createdAt(){
        createdAt=LocalDateTime.now();
    }

    @PreUpdate
    protected void updatedAt(){
        updatedAt=LocalDateTime.now();
    }


}
