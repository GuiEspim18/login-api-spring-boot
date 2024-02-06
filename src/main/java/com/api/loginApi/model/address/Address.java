package com.api.loginApi.model.address;

import com.api.loginApi.model.address.dto.AddressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String zipcode;
    private String number;
    private String compliment;
    private String city;
    private String neighborhood;
    private String uf;

    public Address(AddressDTO data) {
        this.street = data.street();
        this.zipcode = data.zipcode();
        this.number = data.number();
        this.compliment = data.compliment();
        this.city = data.city();
        this.neighborhood = data.neighborhood();
        this.uf = data.uf();
    }

}
