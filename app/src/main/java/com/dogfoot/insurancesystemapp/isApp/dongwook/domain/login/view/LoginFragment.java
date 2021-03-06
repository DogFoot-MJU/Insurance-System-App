package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dogfoot.insurancesystemapp.R;

import com.dogfoot.insurancesystemapp.isApp.MainActivity;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.dongwook.DongWookActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;

import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view.SignUpActivity;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.JungWoo;
import com.dogfoot.insurancesystemapp.mainCrossDomain.domain.model.room.entity.MainEntity;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;


import java.util.Map;

import retrofit2.Response;


public class LoginFragment extends DogFootViewModelFragment {

    // Associate
    // View

    private EditText idText, pwText;
    private Button loginButton,  signInButton;
    private TextView signUp;
    @Override
    protected int getLayoutId() { return R.layout.fragment_login; }
    @Override
    protected void associateView(View view) {
        this.idText = view.findViewById(R.id.loginActivity_idText);
        this.pwText = view.findViewById(R.id.loginActivity_pwText);
        this.loginButton = view.findViewById(R.id.loginActivity_loginButton);
        this.signUp = view.findViewById(R.id.loginActivity_registerTextView);

    }
    @Override
    protected void initializeView() {
        this.loginButton.setOnClickListener(v->this.login());
        this.signUp.setOnClickListener(v-> this.startActivity(new Intent(this.getContext(), SignUpActivity.class)));


    }

    @Override
    protected void dogFootEntityUpdated() {

    }



    private void login(){
        LoginRequest loginRequest = new LoginRequest(this.idText.getText().toString(), this.pwText.getText().toString());
        RetrofitTool.getAPIWithNullConverter().login(loginRequest).enqueue(MainRetrofitTool.getCallback(new LoginRequestCallback()));
    }

    private class LoginRequestCallback implements MainRetrofitCallback<LoginRequest> {
        @Override
        public void onSuccessResponse(Response<LoginRequest> response) {

            dataset.put(DogFootEntity.EDogFootData.AUTHORIZATION, response.headers().get("Authorization"));

            Log.d("?????????",dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION));
            dataset.put(DogFootEntity.EDogFootData.EMAIL, idText.getText().toString());
            dataset.put(DogFootEntity.EDogFootData.PASSWORD, pwText.getText().toString());



            Constant constant = Constant.getInstance();
            Map<DogFootEntity.EDogFootData, String> map = constant.getDataset();
            map.put(DogFootEntity.EDogFootData.AUTHORIZATION, response.headers().get("Authorization"));
            map.put(DogFootEntity.EDogFootData.EMAIL, idText.getText().toString());
            map.put(DogFootEntity.EDogFootData.PASSWORD, pwText.getText().toString());

            DogFootDialog.simplerAlertDialog(getActivity(),
                    R.string.login_success_dialog, R.string.login_success_content_dialog,
                    (dialog, which) -> startMainActivity()
            );
        }
        @Override
        public void onFailResponse(Response<LoginRequest> response) {
            DogFootDialog.simplerAlertDialog(getActivity(),
                    R.string.login_failed_dialog, R.string.login_failed_content_dialog,
                    (dialog, which) -> { idText.setText(""); pwText.setText(""); }
            );
        }
        @Override
        public void onConnectionFail(Throwable t) {
            Log.e("????????????", t.getMessage());
        }
    }

    public void startMainActivity() {

        this.startActivity(new Intent(this.getContext(), JungWoo.class)); }


}
