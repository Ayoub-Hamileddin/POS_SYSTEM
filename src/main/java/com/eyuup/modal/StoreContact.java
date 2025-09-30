package com.eyuup.modal;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.Data;



@Data
@Embeddable
public class StoreContact {


    private String phone;

    @Email(message = "invalid email formate")
    private String email;

    private String address;


}
