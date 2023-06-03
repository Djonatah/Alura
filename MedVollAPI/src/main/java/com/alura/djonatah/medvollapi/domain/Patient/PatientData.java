package com.alura.djonatah.medvollapi.domain.Patient;

import com.alura.djonatah.medvollapi.domain.common.AddressData;

public record PatientData(String name, String email, String phone, String ssn, AddressData address){
}
