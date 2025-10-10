package com.eyuup.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false, unique = true)
    private String sku;

    private String  description ;

    @Column(nullable = false)
    private Double mrp;

    @Column(nullable = false)
    private Double sellingPrice;

    @Column(nullable = true)
    private String brand;

    private String image;


    @ManyToOne
    private Category category;


    @ManyToOne
    private Store store;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
    }



    @PreUpdate
    protected void onUpdate(){
        updatedAt=LocalDateTime.now();
    }

}
