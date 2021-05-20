package com.dogfoot.insurancesystemapp.isApp.crossDomain.tech;




import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingInputFragment;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {
    @POST("login")
    Call<LoginRequest> login(@Body LoginRequest loginRequest);
    @POST("signup")
    Call<SignUpResponse> signup(@Body SignUpRequest signUpRequest);
    @POST("api/v1/user/consulting")
    Call<CustomerConsultingInputResponse> customerConsultingInput(@Body CustomerConsultingInputRequest customerConsultingInputRequest);



    // 보험 상품을 기획한다
    @POST("api/v1/planner/car/product/development/plan")
    Call<CarPlanInsuranceResponse> PlanCarInsurance(@Body CarPlanInsuranceRequest carPlanInsuranceRequest);
    @POST("api/v1/planner/driver/product/development/plan")
    Call<DriverPlanInsuranceResponse> PlanDriverInsurance(@Body DriverPlanInsuranceRequest driverPlanInsuranceRequest);
    @POST("api/v1/planner/fire/product/development/plan")
    Call<FirePlanInsuranceResponse> PlanFireInsurance(@Body FirePlanInsuranceRequest firePlanInsuranceRequest);
    @POST("api/v1/planner/travel/product/development/plan")
    Call<TravelPlanInsuranceResponse> PlanTravelInsurance(@Body TravelPlanInsuranceRequest travelPlanInsuranceRequest);

    // 기획된 보험 상품 리스트를 보여준다.
    @GET("api/v1/planner/car/product/development/list?page=0&state=PLAN")
    Call<Pagination<List<CarPlanInsuranceResponse>>> carPlanInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=PLAN")
    Call<Pagination<List<DriverPlanInsuranceResponse>>> driverPlanInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=PLAN")
    Call<Pagination<List<FirePlanInsuranceResponse>>> firePlanInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=PLAN")
    Call<Pagination<List<TravelPlanInsuranceResponse>>> travelPlanInsurance();

    // 보험 상품을 상세히 보여준다.
    @GET("api/v1/planner/car/product/development/{id}")
    Call<CarPlanInsuranceResponse> getCarInsuracneDetailed(@Path("id") int id);
    @GET("api/v1/planner/driver/product/development/{id}")
    Call<DriverPlanInsuranceResponse> getDriverInsuracneDetailed(@Path("id") int id);
    @GET("api/v1/planner/fire/product/development/{id}")
    Call<FirePlanInsuranceResponse> getFireInsuracneDetailed(@Path("id") int id);
    @GET("api/v1/planner/travel/product/development/{id}")
    Call<TravelPlanInsuranceResponse> getTravelInsuracneDetailed(@Path("id") int id);

    // 기획된 보험 상품을 삭제한다.
    @DELETE("api/v1/planner/car/product/development/{id}")
    Call<Void> deleteCarInsuracne(@Path("id") int id);

    @DELETE("api/v1/planner/driver/product/development/{id}")
    Call<Void> deleteDriverInsuracne(@Path("id") int id);
    @DELETE("api/v1/planner/fire/product/development/{id}")
    Call<Void> deleteFireInsurance(@Path("id") int id);
    @DELETE("api/v1/planner/travel/product/development/{id}")
    Call<Void> deleteTravelInsuracne(@Path("id") int id);

    // 설계된 보험 상품 리스트를 보여준다.
    @GET("api/v1/planner/car/product/development/list?page=0&state=DESIGN")
    Call<Pagination<List<CarDesignInsuranceResponse>>> carDesignInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=DESIGN")
    Call<Pagination<List<DriverDesignInsuranceResponse>>> driverDesignInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=DESIGN")
    Call<Pagination<List<FireDesignInsuranceResponse>>> fireDesignInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=DESIGN")
    Call<Pagination<List<TravelDesignInsuranceResponse>>> travelDesignInsurance();



}
