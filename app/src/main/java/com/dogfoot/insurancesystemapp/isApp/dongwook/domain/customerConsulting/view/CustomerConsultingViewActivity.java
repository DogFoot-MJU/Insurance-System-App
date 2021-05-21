package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingViewResponse;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Response;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.CUSTOMER_CONSULTING__ACTIVITY_ID;
import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.LIST_FRAGMENT_FAILURE_DIALOG_TITLE;

public class CustomerConsultingViewActivity extends DogFootViewModelActivity {

    private TextView mainText,titleText,authorText,timeText ,titleInput,authorInput,timeInput,contentsInput;
    private Button submitButton;
    private Long viewId;
    private LoadingDialog loadingDialog;
    private LocalDateTime timeNow;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_consulting_view);

    }




    @Override
    protected void associateView() {
        this.mainText = findViewById(R.id.user_consult_view_mainTextView);
        this.titleText = findViewById(R.id.user_consult_view_subTextView1);
        this.authorText = findViewById(R.id.user_consult_view_subTextView2);
        this.timeText = findViewById(R.id.user_consult_view_subTextView3);
        this.titleInput = findViewById(R.id.user_consult_view_subTextView1Input);
        this.authorInput = findViewById(R.id.user_consult_view_subTextView2Input);
        this.timeInput = findViewById(R.id.user_consult_view_subTextView3Input);
        this.contentsInput= findViewById(R.id.user_consult_view_contents);
        this.submitButton= findViewById(R.id.user_consult_view_button);
    }


    @Override
    protected void initializeView() {


        this.viewId = getIntent().getLongExtra("viewId", 0L);
        this.submitButton.setOnClickListener(v -> this.submit());
        this.loadConsultingView();


    }

    @Override
    public void dogFootEntityUpdated() {

    }


    private void submit() {
       finish();
    }



    private void loadConsultingView() {


        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getCustomerConsultingView(this.viewId)
                .enqueue(MainRetrofitTool.getCallback(new CustomerConsultingViewResponseCallback()));
    }

    /**
     * Callback
     */
    private class CustomerConsultingViewResponseCallback implements MainRetrofitCallback<CustomerConsultingViewResponse> {

        @Override
        public void onSuccessResponse(Response<CustomerConsultingViewResponse> response) {

            titleInput.setText(response.body().getTitle());
            authorInput.setText(response.body().getWriter());
            timeInput.setText(response.body().getCreation_date());

            contentsInput.setText(response.body().getContents());
        }

        @Override
        public void onFailResponse(Response<CustomerConsultingViewResponse> response) {

            try {

                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.d("디버그", jObjError.getString("error-message"));

            } catch (Exception e) {
                Log.d("디버그",  e.getMessage());

            }

        }

        @Override
        public void onConnectionFail(Throwable t) {


            }

}





}
