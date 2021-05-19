package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("address")
    private String address;

    @SerializedName("resident_registration_number")
    private String resident_registration_number;


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone_number=" + phone_number +
                ", address='" + address + '\'' +
                ", resident_registration_number='" + resident_registration_number + '\'' +
                '}';
    }
}
