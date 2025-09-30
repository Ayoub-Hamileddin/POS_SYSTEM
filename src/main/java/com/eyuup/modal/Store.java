package com.eyuup.modal;

import java.time.LocalDateTime;

import com.eyuup.domain.StoreStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO  )
    private Long  id ;

    private String brand;

    @OneToOne
    private User storeAdmin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact storeContact = new StoreContact() ;


    // ! hibernate take care of createAt and status fields once you create obj
    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
        status=StoreStatus.PENDING;        
    }
    // ! the same thing when you update 
    @PreUpdate
    protected void onUpdate(){
        updatedAt=LocalDateTime.now();
    }

}
