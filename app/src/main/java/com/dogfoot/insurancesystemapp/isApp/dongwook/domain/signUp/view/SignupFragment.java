package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.dogfoot.insurancesystemapp.R;

import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import retrofit2.Response;

public class SignupFragment extends DogFootViewModelFragment {
    private EditText idText, pwText,nameText,phoneText,addressText,residentText;
    private Button signUpButton;
    private LoadingDialog loadingDialog;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signup;
    }

    @Override
    protected void associateView(View view) {
        this.idText = view.findViewById(R.id.signupActivity_idText);
        this.pwText = view.findViewById(R.id.signupActivity_pwText);
        this.nameText = view.findViewById(R.id.signupActivity_nameText);
        this.phoneText = view.findViewById(R.id.signupActivity_phoneText);
        this.addressText = view.findViewById(R.id.signupActivity_addressText);
        this.residentText = view.findViewById(R.id.signupActivity_residentText);
        this.signUpButton = view.findViewById(R.id.signupActivity_signupButton);

    }

    @Override
    protected void initializeView() {
        this.signUpButton.setOnClickListener(v->this.signUp());
    }

    @Override
    protected void dogFootEntityUpdated() {

    }

    private class SignUpRequestCallback implements MainRetrofitCallback<SignUpResponse> {
        @Override
        public void onSuccessResponse(Response<SignUpResponse> response) {
            loadingDialog.hide();
            Navigation.findNavController(getView()).navigate(R.id.action_register1Fragment_to_register2Fragment);

        }
        @Override
        public void onFailResponse(Response<SignUpResponse> response) {
            try {
                loadingDialog.hide();
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(getContext(), jObjError.getString("error-message"), Toast.LENGTH_LONG).show();
            } catch (Exception e) { Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show(); }
        }
        @Override
        public void onConnectionFail(Throwable t) {
            loadingDialog.hide();
            Log.d("회원가입 실패! : 인터넷 연결을 확인해 주세요", t.getMessage());
        }
    }





    /**
     * Method
     */
    private void signUp() {
        this.loadingDialog= new LoadingDialog(this.getContext());
        this.loadingDialog.show();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail(this.idText.getText().toString());
        signUpRequest.setPassword(this.pwText.getText().toString());
        signUpRequest.setName(this.nameText.getText().toString());
        signUpRequest.setPhone_number(this.phoneText.getText().toString());
        signUpRequest.setAddress(this.addressText.getText().toString());
        signUpRequest.setResident_registration_number(this.residentText.getText().toString());
        RetrofitTool.getAPI().signup(signUpRequest).enqueue(MainRetrofitTool.getCallback(new SignUpRequestCallback()));
    }
}
