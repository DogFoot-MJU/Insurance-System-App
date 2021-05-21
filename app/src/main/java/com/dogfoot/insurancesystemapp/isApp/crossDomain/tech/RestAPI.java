package com.dogfoot.insurancesystemapp.isApp.crossDomain.tech;




import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingViewResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestAPI {
    @POST("login")
    Call<LoginRequest> login(@Body LoginRequest loginRequest);
    @POST("signup")
    Call<SignUpResponse> signup(@Body SignUpRequest signUpRequest);
    @POST("api/v1/user/consulting")
    Call<CustomerConsultingInputResponse> customerConsultingInput(@Body CustomerConsultingInputRequest customerConsultingInputRequest);
    @GET("api/v1/user/consulting/list")
    Call<Pagination<List<CustomerConsultingListResponse>>> getCustomerConsultingList();
    @GET("api/v1/user/consulting/{id}")
    Call<CustomerConsultingViewResponse> getCustomerConsultingView(@Path("id") Long id);




    // 보험 상품을 기획한다
    @POST("api/v1/planner/car/product/development/plan")
    Call<CarPlanInsuranceResponse> planCarInsurance(@Body CarPlanInsuranceRequest carPlanInsuranceRequest);
    @POST("api/v1/planner/driver/product/development/plan")
    Call<DriverPlanInsuranceResponse> planDriverInsurance(@Body DriverPlanInsuranceRequest driverPlanInsuranceRequest);
    @POST("api/v1/planner/fire/product/development/plan")
    Call<FirePlanInsuranceResponse> planFireInsurance(@Body FirePlanInsuranceRequest firePlanInsuranceRequest);
    @POST("api/v1/planner/travel/product/development/plan")
    Call<TravelPlanInsuranceResponse> planTravelInsurance(@Body TravelPlanInsuranceRequest travelPlanInsuranceRequest);

    // 기획된 보험 상품 리스트를 보여준다.
    @GET("api/v1/planner/car/product/development/list?page=0&state=PLAN")
    Call<Pagination<List<CarPlanInsuranceResponse>>> carPlanInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=PLAN")
    Call<Pagination<List<DriverPlanInsuranceResponse>>> driverPlanInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=PLAN")
    Call<Pagination<List<FirePlanInsuranceResponse>>> firePlanInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=PLAN")
    Call<Pagination<List<TravelPlanInsuranceResponse>>> travelPlanInsurance();

    // 기획된 보험 상품을 삭제한다.
    @DELETE("api/v1/planner/car/product/development/{id}")
    Call<Void> deleteCarInsuracne(@Path("id") Long id);
    @DELETE("api/v1/planner/driver/product/development/{id}")
    Call<Void> deleteDriverInsuracne(@Path("id") Long id);
    @DELETE("api/v1/planner/fire/product/development/{id}")
    Call<Void> deleteFireInsurance(@Path("id") Long id);
    @DELETE("api/v1/planner/travel/product/development/{id}")
    Call<Void> deleteTravelInsuracne(@Path("id") Long id);

    // 설계된 보험 상품 리스트를 보여준다.
    @GET("api/v1/planner/car/product/development/list?page=0&state=DESIGN")
    Call<Pagination<List<CarDesignInsuranceResponse>>> carDesignInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=DESIGN")
    Call<Pagination<List<DriverDesignInsuranceResponse>>> driverDesignInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=DESIGN")
    Call<Pagination<List<FireDesignInsuranceResponse>>> fireDesignInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=DESIGN")
    Call<Pagination<List<TravelDesignInsuranceResponse>>> travelDesignInsurance();

    // 보험상품을 설계한다.
    @PUT("api/v1/planner/car/product/development/design")
    Call<CarDesignInsuranceResponse> designCarInsurance(@Body CarDesignInsuranceRequest CarDesignInsuranceRequest);
    @PUT("api/v1/planner/driver/product/development/design")
    Call<DriverDesignInsuranceResponse> designDriverInsurance(@Body DriverDesignInsuranceRequest driverDesignInsuranceRequest);
    @PUT("api/v1/planner/fire/product/development/design")
    Call<FireDesignInsuranceResponse> designFireInsurance(@Body FireDesignInsuranceRequest fireDesignInsuranceRequest);
    @PUT("api/v1/planner/travel/product/development/design")
    Call<TravelDesignInsuranceResponse> designTravelInsurance(@Body TravelDesignInsuranceRequest travelDesignInsuranceRequest);

    // 보험 상품을 상세히 보여준다.
    @GET("api/v1/planner/car/product/development/{id}")
    Call<CarDesignInsuranceResponse> getCarInsuracneDetailed(@Path("id") Long id);
    @GET("api/v1/planner/driver/product/development/{id}")
    Call<DriverDesignInsuranceResponse> getDriverInsuracneDetailed(@Path("id") Long id);
    @GET("api/v1/planner/fire/product/development/{id}")
    Call<FireDesignInsuranceResponse> getFireInsuracneDetailed(@Path("id") Long id);
    @GET("api/v1/planner/travel/product/development/{id}")
    Call<TravelDesignInsuranceResponse> getTravelInsuracneDetailed(@Path("id") Long id);

    // 보험 상품 인가하기
    @PUT("api/v1/planner/car/product/development/authorize/{id}")
    Call<Void> authorizeCarInsurance(@Path("id") Long id);
    @PUT("api/v1/planner/driver/product/development/authorize/{id}")
    Call<Void> authorizeDriverInsurance(@Path("id") Long id);
    @PUT("api/v1/planner/fire/product/development/authorize/{id}")
    Call<Void> authorizeFireInsurance(@Path("id") Long id);
    @PUT("api/v1/planner/travel/product/development/authorize/{id}")
    Call<Void> authorizeTravelInsurance(@Path("id") Long id);

    // 보험 상품 승인하기
    @PUT("api/v1/financial/supervisory/car/product/development/approve/{id}")
    Call<Void> approveCarInsurance(@Path("id") Long id);
    @PUT("api/v1/financial/supervisory/driver/product/development/approve/{id}")
    Call<Void> approveDriverInsurance(@Path("id") Long id);
    @PUT("api/v1/financial/supervisory/fire/product/development/approve/{id}")
    Call<Void> approveFireInsurance(@Path("id") Long id);
    @PUT("api/v1/financial/supervisory/travel/product/development/approve/{id}")
    Call<Void> approveTravelInsurance(@Path("id") Long id);

    // 인가된 보험 상품 리스트를 보여준다.
    @GET("api/v1/planner/car/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<CarDesignInsuranceResponse>>> getAuthorizedCarInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<DriverDesignInsuranceResponse>>> getAuthorizedDriverInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<FireDesignInsuranceResponse>>> getAuthorizedFireInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<TravelDesignInsuranceResponse>>> getAuthorizedTravelInsurance();

}
