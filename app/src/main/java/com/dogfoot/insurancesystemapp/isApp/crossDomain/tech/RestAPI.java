package com.dogfoot.insurancesystemapp.isApp.crossDomain.tech;

import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CompensationResultListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.CustomerContractListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingAnsweredViewResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingViewResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.ImageUploadResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationContractDetailResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationContractListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationPayRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationPayResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationRejectResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.Files;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingInputRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingInputResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyUpdateRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.PaymentResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.TravelDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.DriverInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.DriverInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.FireInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.FireInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.TravelInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.TravelInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.CarLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.DriverLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.FireLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.TravelLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.Pagination;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceRequest;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.user.model.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("api/v1/user/consulting/answer/{id}")
    Call<CustomerConsultingAnsweredViewResponse> getCustomerConsultingAnsweredView(@Path("id") Long id);

    @Multipart
    @POST("api/v1/user/accident/reception/{id}")
    Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part uploadFile,@Path("id") Long id);

    @GET("api/v1/seller/consulting/list")
    Call<Pagination<List<SalesConsultingListResponse>>> getSalesConsultingList();

    @POST("api/v1/seller/consulting/answer")
    Call<SalesConsultingInputResponse> salesConsultingInput(@Body SalesConsultingInputRequest salesConsultingInputRequest);

    @GET("api/v1/user/my/contract")
    Call<List<CustomerContractListResponse>> getCustomerContract();

    @GET("api/v1/compensation-handler/accident/list?state=WAIT")
    Call<List<CompensationContractListResponse>> getCompensationContract();

    @GET("api/v1/compensation-handler/accident/detail/{id}")
    Call<CompensationContractDetailResponse<List<Files>>> getCompensationContractDetail(@Path("id") Long id);

    @POST("api/v1/compensation-handler/accident/approve")
    Call<CompensationPayResponse> CompensationPay(@Body CompensationPayRequest compensationPayRequest);

    @PUT("api/v1/compensation-handler/accident/reject/{id}")
    Call<CompensationRejectResponse> CompensationReject(@Path("id") Long id);

    @GET("api/v1/user/accident/list")
    Call<List<CompensationResultListResponse>> getCompensationResult();



    // ?????? ????????? ????????????
    @POST("api/v1/planner/car/product/development/plan")
    Call<CarPlanInsuranceResponse> planCarInsurance(@Body CarPlanInsuranceRequest carPlanInsuranceRequest);
    @POST("api/v1/planner/driver/product/development/plan")
    Call<DriverPlanInsuranceResponse> planDriverInsurance(@Body DriverPlanInsuranceRequest driverPlanInsuranceRequest);
    @POST("api/v1/planner/fire/product/development/plan")
    Call<FirePlanInsuranceResponse> planFireInsurance(@Body FirePlanInsuranceRequest firePlanInsuranceRequest);
    @POST("api/v1/planner/travel/product/development/plan")
    Call<TravelPlanInsuranceResponse> planTravelInsurance(@Body TravelPlanInsuranceRequest travelPlanInsuranceRequest);

    // ????????? ?????? ?????? ???????????? ????????????.
    @GET("api/v1/planner/car/product/development/list?page=0&state=PLAN")
    Call<Pagination<List<CarPlanInsuranceResponse>>> carPlanInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=PLAN")
    Call<Pagination<List<DriverPlanInsuranceResponse>>> driverPlanInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=PLAN")
    Call<Pagination<List<FirePlanInsuranceResponse>>> firePlanInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=PLAN")
    Call<Pagination<List<TravelPlanInsuranceResponse>>> travelPlanInsurance();

    // ????????? ?????? ????????? ????????????.
    @DELETE("api/v1/planner/car/product/development/{id}")
    Call<Void> deleteCarInsuracne(@Path("id") Long id);
    @DELETE("api/v1/planner/driver/product/development/{id}")
    Call<Void> deleteDriverInsuracne(@Path("id") Long id);
    @DELETE("api/v1/planner/fire/product/development/{id}")
    Call<Void> deleteFireInsurance(@Path("id") Long id);
    @DELETE("api/v1/planner/travel/product/development/{id}")
    Call<Void> deleteTravelInsuracne(@Path("id") Long id);

    // ????????? ?????? ?????? ???????????? ????????????.
    @GET("api/v1/planner/car/product/development/list?state=DESIGN")
    Call<Pagination<List<CarDesignInsuranceResponse>>> carDesignInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=DESIGN")
    Call<Pagination<List<DriverDesignInsuranceResponse>>> driverDesignInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=DESIGN")
    Call<Pagination<List<FireDesignInsuranceResponse>>> fireDesignInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=DESIGN")
    Call<Pagination<List<TravelDesignInsuranceResponse>>> travelDesignInsurance();

    // ??????????????? ????????????.
    @PUT("api/v1/planner/car/product/development/design")
    Call<CarDesignInsuranceResponse> designCarInsurance(@Body CarDesignInsuranceRequest CarDesignInsuranceRequest);
    @PUT("api/v1/planner/driver/product/development/design")
    Call<DriverDesignInsuranceResponse> designDriverInsurance(@Body DriverDesignInsuranceRequest driverDesignInsuranceRequest);
    @PUT("api/v1/planner/fire/product/development/design")
    Call<FireDesignInsuranceResponse> designFireInsurance(@Body FireDesignInsuranceRequest fireDesignInsuranceRequest);
    @PUT("api/v1/planner/travel/product/development/design")
    Call<TravelDesignInsuranceResponse> designTravelInsurance(@Body TravelDesignInsuranceRequest travelDesignInsuranceRequest);

    // ?????? ????????? ????????? ????????????.
    @GET("api/v1/planner/car/product/development/{id}")
    Call<CarDesignInsuranceResponse> getCarInsuracneDetailed(@Path("id") Long id);
    @GET("api/v1/planner/driver/product/development/{id}")
    Call<DriverDesignInsuranceResponse> getDriverInsuracneDetailed(@Path("id") Long id);
    @GET("api/v1/planner/fire/product/development/{id}")
    Call<FireDesignInsuranceResponse> getFireInsuracneDetailed(@Path("id") Long id);
    @GET("api/v1/planner/travel/product/development/{id}")
    Call<TravelDesignInsuranceResponse> getTravelInsuracneDetailed(@Path("id") Long id);

    // ?????? ?????? ????????????
    @PUT("api/v1/planner/car/product/development/authorize/{id}")
    Call<Void> authorizeCarInsurance(@Path("id") Long id);
    @PUT("api/v1/planner/driver/product/development/authorize/{id}")
    Call<Void> authorizeDriverInsurance(@Path("id") Long id);
    @PUT("api/v1/planner/fire/product/development/authorize/{id}")
    Call<Void> authorizeFireInsurance(@Path("id") Long id);
    @PUT("api/v1/planner/travel/product/development/authorize/{id}")
    Call<Void> authorizeTravelInsurance(@Path("id") Long id);

    // ?????? ?????? ????????????
    @PUT("api/v1/financial/supervisory/car/product/development/approve/{id}")
    Call<Void> approveCarInsurance(@Path("id") Long id);
    @PUT("api/v1/financial/supervisory/driver/product/development/approve/{id}")
    Call<Void> approveDriverInsurance(@Path("id") Long id);
    @PUT("api/v1/financial/supervisory/fire/product/development/approve/{id}")
    Call<Void> approveFireInsurance(@Path("id") Long id);
    @PUT("api/v1/financial/supervisory/travel/product/development/approve/{id}")
    Call<Void> approveTravelInsurance(@Path("id") Long id);

    // ????????? ?????? ?????? ???????????? ????????????.
    @GET("api/v1/planner/car/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<CarDesignInsuranceResponse>>> getAuthorizedCarInsurance();
    @GET("api/v1/planner/driver/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<DriverDesignInsuranceResponse>>> getAuthorizedDriverInsurance();
    @GET("api/v1/planner/fire/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<FireDesignInsuranceResponse>>> getAuthorizedFireInsurance();
    @GET("api/v1/planner/travel/product/development/list?state=AUTHORIZE")
    Call<Pagination<List<TravelDesignInsuranceResponse>>> getAuthorizedTravelInsurance();

    // ??????????????? ????????????.
    @POST("api/v1/uw/capacity")
    Call<Void> registrationCapacityPolicy(@Body CapacityPolicyRequest capacityPolicyRequest);

    // ???????????? ???????????? ????????????.
    @GET("api/v1/uw/capacity/list")
    Call<Pagination<List<CapacityPolicyResponse>>> getCapacityPolicyList();

    // ??????????????? ????????????.
    @DELETE("api/v1/uw/capacity/{id}")
    Call<Void> deleteCapacityPolicy(@Path("id") Long id);

    // ???????????? ????????????
    @GET("api/v1/uw/capacity/{id}")
    Call<CapacityPolicyResponse> getCapacityPolicyDetailed(@Path("id") Long id);

    // ??????????????? ????????? ?????????????????? ????????????.
    @GET("api/v1/user/car/insurance/unavailable/list")
    Call<Pagination<List<CarInsuranceResponse>>> getPossibleCapacityCarInsuranceList();
    @GET("api/v1/user/driver/insurance/unavailable/list")
    Call<Pagination<List<DriverInsuranceResponse>>> getPossibleCapacityDriverInsuranceList();
    @GET("api/v1/user/fire/insurance/unavailable/list")
    Call<Pagination<List<FireInsuranceResponse>>> getPossibleCapacityFireInsuranceList();
    @GET("api/v1/user/travel/insurance/unavailable/list")
    Call<Pagination<List<TravelInsuranceResponse>>> getPossibleCapacityTravelInsuranceList();

    // ???????????? ????????????
    @PUT("api/v1/uw/capacity")
    Call<Void> updateCapacityPolicy(@Body CapacityPolicyUpdateRequest capacityPolicyUpdateRequest);

    // ???????????? ?????? ?????????
    @GET("api/v1/user/car/insurance/available/list")
    Call<Pagination<List<CarInsuranceResponse>>> getPossibleCarInsuranceList();
    @GET("api/v1/user/driver/insurance/available/list")
    Call<Pagination<List<DriverInsuranceResponse>>> getPossibleDriverInsuranceList();
    @GET("api/v1/user/fire/insurance/available/list")
    Call<Pagination<List<FireInsuranceResponse>>> getPossibleFireInsuranceList();
    @GET("api/v1/user/travel/insurance/available/list")
    Call<Pagination<List<TravelInsuranceResponse>>> getPossibleTravelInsuranceList();

    // ?????? ????????????
    @POST("api/v1/user/contract/car/apply")
    Call<Void> applyForCarInsurance(@Body CarInsuranceRequest carInsuranceRequest);
    @POST("api/v1/user/contract/driver/apply")
    Call<Void> applyForDriverInsurance(@Body DriverInsuranceRequest driverInsuranceRequest);
    @POST("api/v1/user/contract/fire/apply")
    Call<Void> applyForFireInsurance(@Body FireInsuranceRequest fireInsuranceRequest);
    @POST("api/v1/user/contract/travel/apply")
    Call<Void> applyForTravelInsurance(@Body TravelInsuranceRequest travelInsuranceRequest);

    // ??????????????? ?????? ????????????
    @GET("api/v1/user/car/insurance/{id}")
    Call<CarInsuranceResponse> getPossibleCarInsuranceDetailed(@Path("id") Long id);
    @GET("api/v1/user/driver/insurance/{id}")
    Call<DriverInsuranceResponse> getPossibleDriverInsuranceDetailed(@Path("id") Long id);
    @GET("api/v1/user/fire/insurance/{id}")
    Call<FireInsuranceResponse> getPossibleFireInsuranceDetailed(@Path("id") Long id);
    @GET("api/v1/user/travel/insurance/{id}")
    Call<TravelInsuranceResponse> getPossibleTravelInsuranceDetailed(@Path("id") Long id);

    // ?????? ?????? ????????????
    @POST("api/v1/user/contract/car/calculate")
    Call<PaymentResponse> calculateCarInsurancePrice(@Body CarInsuranceRequest carInsuranceRequest);
    @POST("api/v1/user/contract/driver/calculate")
    Call<PaymentResponse> calculateDriverInsurancePrice(@Body DriverInsuranceRequest driverInsuranceRequest);
    @POST("api/v1/user/contract/fire/calculate")
    Call<PaymentResponse> calculateFireInsurancePrice(@Body FireInsuranceRequest fireInsuranceRequest);
    @POST("api/v1/user/contract/travel/calculate")
    Call<PaymentResponse> calculateTravelInsurancePrice(@Body TravelInsuranceRequest travelInsuranceRequest);

    // ?????? ?????? ?????????
    @GET("api/v1/uw/contract/car/wait/list")
    Call<Pagination<List<CarLegitimateExaminationResponse>>> getCarLegitimateExaminationList();
    @GET("api/v1/uw/contract/driver/wait/list")
    Call<Pagination<List<DriverLegitimateExaminationResponse>>> getDriverLegitimateExaminationList();
    @GET("api/v1/uw/contract/fire/wait/list")
    Call<Pagination<List<FireLegitimateExaminationResponse>>> getFireLegitimateExaminationList();
    @GET("api/v1/uw/contract/travel/wait/list")
    Call<Pagination<List<TravelLegitimateExaminationResponse>>> getTravelLegitimateExaminationList();

    // ?????? ????????????
    @GET("api/v1/uw/contract/car/wait/detail/{id}")
    Call<CarLegitimateExaminationResponse> getCarLegitimateExaminationDetailed(@Path("id") Long id);
    @GET("api/v1/uw/contract/driver/wait/detail/{id}")
    Call<DriverLegitimateExaminationResponse> getDriverLegitimateExaminationDetailed(@Path("id") Long id);
    @GET("api/v1/uw/contract/fire/wait/detail/{id}")
    Call<FireLegitimateExaminationResponse> getFireLegitimateExaminationDetailed(@Path("id") Long id);
    @GET("api/v1/uw/contract/travel/wait/detail/{id}")
    Call<TravelLegitimateExaminationResponse> getTravelLegitimateExaminationDetailed(@Path("id") Long id);

    // ?????? ????????????
    @PUT("api/v1/uw/contract/car/approve/{id}")
    Call<Void> approveLegitimateExaminationCar(@Path("id") Long id);
    @PUT("api/v1/uw/contract/driver/approve/{id}")
    Call<Void> approveLegitimateExaminationDriver(@Path("id") Long id);
    @PUT("api/v1/uw/contract/fire/approve/{id}")
    Call<Void> approveLegitimateExaminationFire(@Path("id") Long id);
    @PUT("api/v1/uw/contract/travel/approve/{id}")
    Call<Void> approveLegitimateExaminationTravel(@Path("id") Long id);

    // ?????? ????????????
    @PUT("api/v1/uw/contract/car/reject/{id}")
    Call<Void> rejectLegitimateExaminationCar(@Path("id") Long id);
    @PUT("api/v1/uw/contract/driver/reject/{id}")
    Call<Void> rejectLegitimateExaminationDriver(@Path("id") Long id);
    @PUT("api/v1/uw/contract/fire/reject/{id}")
    Call<Void> rejectLegitimateExaminationFire(@Path("id") Long id);
    @PUT("api/v1/uw/contract/travel/reject/{id}")
    Call<Void> rejectLegitimateExaminationTravel(@Path("id") Long id);

    // ?????? ??? ??????
    @GET("api/v1/user/info")
    Call<UserResponse> getRole();
}
