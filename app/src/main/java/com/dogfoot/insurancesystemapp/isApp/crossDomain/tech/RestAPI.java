package com.dogfoot.insurancesystemapp.isApp.crossDomain.tech;




import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestAPI {
    @POST("login")
    Call<LoginRequest> login(@Body LoginRequest loginRequest);

}
