package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.MainActivity;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.DongWookActivity;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingInputResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.model.LoginRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.login.view.LoginFragment;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpResponse;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import retrofit2.Response;

public class CustomerConsultingInputFragment extends DogFootViewModelFragment {

    private EditText titleText, contentText;
    private Button submitButton;
    private TextView titleView,subTextView;
    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_consulting_input;
    }

    @Override
    protected void associateView(View view) {
        this.titleText = view.findViewById(R.id.user_consult_input_editText);
        this.titleText.setHint("제목을 입력하세요.");
        this.contentText = view.findViewById(R.id.user_consult_input_editText2);
        this.contentText.setHint("내용을 입력하세요.");
        this.submitButton = view.findViewById(R.id.user_consult_input_button);
        this.submitButton.setText("제출");
        this.titleView = view.findViewById(R.id.user_consult_input_mainTextView);
        this.titleView.setText("상담 내용 입력");
        this.subTextView= view.findViewById(R.id.user_consult_input_subTextView);
        this.subTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initializeView() {

        this.submitButton.setOnClickListener(v -> this.submit());
    }

    @Override
    protected void dogFootEntityUpdated() {
        if (this.dataset.containsKey(DogFootEntity.EDogFootData.AUTHORIZATION)) {

        } else {
            DogFootDialog.simplerAlertDialog(this.getActivity(),
                    R.string.login_not_signed_dialog, R.string.login_not_signed_content_dialog,
                    (dialog, which) -> startMainActivity()
            );
        }

    }


    private void submit() {
        CustomerConsultingInputRequest customerConsultingInputRequest= new CustomerConsultingInputRequest();
        customerConsultingInputRequest.setTitle(this.titleText.getText().toString());
        customerConsultingInputRequest.setContents(this.contentText.getText().toString());
        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).customerConsultingInput(customerConsultingInputRequest).enqueue(MainRetrofitTool.getCallback(new CustomerConsultingInputRequestCallback()));
        this.loadingDialog= new LoadingDialog(this.getContext());
        this.loadingDialog.show();
    }

    public void startMainActivity() {
        this.startActivity(new Intent(this.getContext(), DongWookActivity.class));
    }



    private class CustomerConsultingInputRequestCallback implements MainRetrofitCallback<CustomerConsultingInputResponse> {
        @Override
        public void onSuccessResponse(Response<CustomerConsultingInputResponse> response) {
            loadingDialog.hide();
            Navigation.findNavController(getView()).navigate(R.id.action_customerConsultingInput_to_customerConsultingMain);
            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
        @Override
        public void onFailResponse(Response<CustomerConsultingInputResponse> response) {
            Log.d("디버그",dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION));
            try {
                loadingDialog.hide();
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(getContext(), jObjError.getString("error-message"), Toast.LENGTH_LONG).show();
            } catch (Exception e) { Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show(); }
        }
        @Override
        public void onConnectionFail(Throwable t) {

            loadingDialog.hide();
            Navigation.findNavController(getView()).navigate(R.id.action_customerConsultingInput_to_customerConsultingMain);
            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

}
