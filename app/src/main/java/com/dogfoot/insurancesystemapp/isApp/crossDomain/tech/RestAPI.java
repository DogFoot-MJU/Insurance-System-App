package com.dogfoot.insurancesystemapp.isApp.crossDomain.tech;




import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelInsuranceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {
    @POST("login")
    Call<LoginRequest> login(@Body LoginRequest loginRequest);
    @POST("signup")
    Call<SignUpResponse> signup(@Body SignUpRequest signUpRequest);

    @GET("api/v1/planner/car/product/development/list?page=0&state=PLAN")
    Call<Pagination<List<CarPlanInsuranceResponse>>> carPlanInsurance();
    @GET("api/v1/planner/car/driver/development/list?state=PLAN")
    Call<List<DriverPlanInsuranceResponse>> driverPlanInsurance();
    @GET("api/v1/planner/car/fire/development/list?state=PLAN")
    Call<List<FirePlanInsuranceResponse>> firePlanInsurance();
    @GET("api/v1/planner/car/driver/development/list?state=PLAN")
    Call<List<TravelInsuranceResponse>> travelInsuranceRequest();

    @GET("api/v1/planner/car/product/development/{id}")
    Call<CarPlanInsuranceResponse> getCarInsuracneDetailed(@Path("id") int id);
    @GET("api/v1/planner/driver/product/development/{id}")
    Call<DriverPlanInsuranceResponse> getDriverInsuracneDetailed(@Path("id") int id);
    @GET("api/v1/planner/fire/product/development/{id}")
    Call<FirePlanInsuranceResponse> getFireInsuracneDetailed(@Path("id") int id);
    @GET("api/v1/planner/travel/product/development/{id}")
    Call<TravelInsuranceResponse> getTravelInsuracneDetailed(@Path("id") int id);

}
