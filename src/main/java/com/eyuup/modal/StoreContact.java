package com.eyuup.modal;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreContact {


    private String phone;

    @Email(message = "invalid email formate")
    private String email;

    private String address;


}
