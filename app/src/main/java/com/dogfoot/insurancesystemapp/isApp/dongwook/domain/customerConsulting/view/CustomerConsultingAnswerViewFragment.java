package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.DogFootViewModelFragment;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingAnsweredViewResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingViewResponse;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import retrofit2.Response;

public class CustomerConsultingAnswerViewFragment extends DogFootViewModelFragment {

    private TextView mainText, titleText, authorText, timeText, titleInput, authorInput, timeInput, contentsInput;
    private Button submitButton;
    private Long viewId;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_consulting_answered_view;
    }


    @Override
    protected void associateView(View view) {
        this.mainText = view.findViewById(R.id.user_consult_view_answer_mainTextView);
        this.titleText = view.findViewById(R.id.user_consult_view_answer_subTextView1);
        this.authorText = view.findViewById(R.id.user_consult_view_answer_subTextView2);
        this.timeText = view.findViewById(R.id.user_consult_view_answer_subTextView3);
        this.titleInput = view.findViewById(R.id.user_consult_view_answer_subTextView1Input);
        this.authorInput = view.findViewById(R.id.user_consult_view_answer_subTextView2Input);
        this.timeInput = view.findViewById(R.id.user_consult_view_answer_subTextView3Input);
        this.contentsInput = view.findViewById(R.id.user_consult_view_answer_contents);
        this.submitButton = view.findViewById(R.id.user_consult_view_answer_button);


    }

    @Override
    protected void initializeView() {
        this.submitButton.setOnClickListener(v -> this.submit());
        this.loadConsultingAnsweredView(Long.parseLong(dataset.get(DogFootEntity.EDogFootData.ANSWERED_ID)));


    }

    @Override
    public void dogFootEntityUpdated() {

    }


    private void submit() {
        Navigation.findNavController(getView()).navigate(R.id.action_customerConsultingAnswerView_to_customerConsultingView);
    }

    private void loadConsultingAnsweredView(Long id) {

        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getCustomerConsultingAnsweredView(id)
                .enqueue(MainRetrofitTool.getCallback(new CustomerConsultingAnsweredViewResponseCallback()));
    }

    /**
     * Callback
     */
    private class CustomerConsultingAnsweredViewResponseCallback implements MainRetrofitCallback<CustomerConsultingAnsweredViewResponse> {

        @Override
        public void onSuccessResponse(Response<CustomerConsultingAnsweredViewResponse> response) {

            titleInput.setText(response.body().getTitle());
            authorInput.setText(response.body().getWriter());
            timeInput.setText(response.body().getCreation_date());
            contentsInput.setText(response.body().getContents());

        }

        @Override
        public void onFailResponse(Response<CustomerConsultingAnsweredViewResponse> response) {

            try {

                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.d("디버그", jObjError.getString("error-message"));

            } catch (Exception e) {
                Log.d("디버그", e.getMessage());

            }

        }

        @Override
        public void onConnectionFail(Throwable t) {


        }

    }


}
