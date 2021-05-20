package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.AbstractFragment_1EditText;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import retrofit2.Response;

public class RegisterResidentFragmentEditText extends AbstractFragment_1EditText {

    // Component
        // View
        private LoadingDialog loadingDialog;

    /**
     * System Life Cycle Callback
     */
    @Override
    protected void initializeView() {
        super.initializeView();
        this.mainTextView.setText(getString(R.string.register_resident_maintext));
        this.subTextView.setVisibility(View.INVISIBLE);
        this.editText.setHint(getString(R.string.register_resident_hint));
        this.button.setText(getString(R.string.button_ok));
    }



    /**
     * Callback
     */
    @Override
    protected void buttonClicked() {
        this.signUp();

    }

    private class SignUpRequestCallback implements MainRetrofitCallback<SignUpResponse> {
        @Override
        public void onSuccessResponse(Response<SignUpResponse> response) {

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
            Navigation.findNavController(getView()).navigate(R.id.action_register6Fragment_to_registerFinishedFragment);
            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

        }
    }





    /**
     * Method
     */
    private void signUp() {
        this.dataset.put(DogFootEntity.EDogFootData.RESIDENT, this.editText.getText().toString());
        this.save();
        this.loadingDialog= new LoadingDialog(this.getContext());
        this.loadingDialog.show();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail(this.dataset.get(DogFootEntity.EDogFootData.EMAIL));
        signUpRequest.setPassword(this.dataset.get(DogFootEntity.EDogFootData.PASSWORD));
        signUpRequest.setName(this.dataset.get(DogFootEntity.EDogFootData.NAME));
        signUpRequest.setPhone_number(this.dataset.get(DogFootEntity.EDogFootData.PHONE));
        signUpRequest.setAddress(this.dataset.get(DogFootEntity.EDogFootData.ADDRESS));
        signUpRequest.setResident_registration_number(this.dataset.get(DogFootEntity.EDogFootData.RESIDENT));
        RetrofitTool.getAPI().signup(signUpRequest).enqueue(MainRetrofitTool.getCallback(new SignUpRequestCallback()));
    }
}