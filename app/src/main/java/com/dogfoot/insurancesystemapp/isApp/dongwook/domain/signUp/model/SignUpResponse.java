package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponse {

    @SerializedName("email")
    private String email;

    @SerializedName("message")
    private Timestamp message;
}
