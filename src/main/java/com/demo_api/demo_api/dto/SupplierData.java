package com.demo_api.demo_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierData {


    @NotEmpty(message = "supplier name is required!!")
    private String name;

    @NotEmpty(message = "supplier address is required!!")
    private String address;

    @NotEmpty(message = "supplier email is required!!")
    @Email(message = "supplier email is not valid!!")
    private String email;

    
}
