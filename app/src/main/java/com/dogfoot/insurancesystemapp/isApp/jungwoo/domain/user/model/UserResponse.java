package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.user.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("role")
    private String role;
    @SerializedName("role_name")
    private String role_name;
}
