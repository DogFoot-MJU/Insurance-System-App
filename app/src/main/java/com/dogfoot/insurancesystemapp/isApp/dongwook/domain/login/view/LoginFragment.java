package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dogfoot.insurancesystemapp.R;

import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.dongwook.DongWookActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;

import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;



import retrofit2.Response;


public class LoginFragment extends DogFootViewModelFragment {

    // Associate
    // View
    private EditText idText, pwText;
    private Button loginButton,  signInButton;
    @Override
    protected int getLayoutId() { return R.layout.fragment_login; }
    @Override
    protected void associateView(View view) {
        this.idText = view.findViewById(R.id.loginActivity_idText);
        this.pwText = view.findViewById(R.id.loginActivity_pwText);
        this.loginButton = view.findViewById(R.id.loginActivity_loginButton);

    }
    @Override
    protected void initializeView() {
        this.loginButton.setOnClickListener(v->this.login());
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
            dataset.put(DogFootEntity.EDogFootData.EMAIL, idText.getText().toString());
            dataset.put(DogFootEntity.EDogFootData.PASSWORD, pwText.getText().toString());
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
            Log.e("연결실패", t.getMessage());
        }
    }

    public void startMainActivity() { this.startActivity(new Intent(this.getContext(), DongWookActivity.class)); }


}
