package com.alura.djonatah.medvollapi.domain.common;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address{
    private String street;
    private String city;
    private String zone;
    private String state;
    private String zip;
    private String number;
    private String additionalInfo;

    public Address(AddressData addressData){
        this.street = addressData.street();
        this.city = addressData.city();
        this.zone = addressData.zone();
        this.state = addressData.state();
        this.zip  = addressData.zip();
        this.number = addressData.number();
        this.additionalInfo = addressData.additionalInfo();
    }
}
