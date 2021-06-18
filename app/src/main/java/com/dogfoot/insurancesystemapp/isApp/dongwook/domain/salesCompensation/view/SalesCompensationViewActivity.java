package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.ImageUploadResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingAnsweredViewResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingAnswerViewFragment;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationContractDetailResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationPayRequest;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationPayResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.CompensationRejectResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model.Files;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.model.SignUpRequest;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;
import com.github.chrisbanes.photoview.PhotoView;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.URL;

public class SalesCompensationViewActivity extends DogFootViewModelActivity {


    private PhotoView imageView;
    private Button approveButton, rejectButton;
    private String fileName;
    private Long id;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_compensation_view);

    }

    @Override
    protected void associateView() {
        imageView = findViewById(R.id.sales_compensation_imageView);
        approveButton = findViewById(R.id.sales_compensation_approveButton);
        rejectButton = findViewById(R.id.sales_compensation_rejectButton);

    }


    @Override
    protected void initializeView() {
        Log.d("디버그",dataset.get(DogFootEntity.EDogFootData.ID));
        this.id=Long.parseLong(dataset.get(DogFootEntity.EDogFootData.ID));
        getDetail();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                getImage();
            }
        }, 500);// 0.5초 정도 딜레이를 준 후 시작

        this.approveButton.setOnClickListener(v -> approve());
        this.rejectButton.setOnClickListener(v -> reject());



    }

    private void approve() {
        final LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.dialog_input, null);

        new AlertDialog.Builder(this)
                .setView(linear)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        EditText url = (EditText) linear.findViewById(R.id.money);
                        String value = url.getText().toString();
                        payCompensation(value);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .show();
    }



    public static void simpleInputDialog(Activity activity, String title, String message, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener cancelListener){
        DogFootDialog dogFootDialog= new DogFootDialog(activity);
        dogFootDialog.setTitle(title);
        dogFootDialog.setMessage(message);
        EditText money = new EditText(activity);
        dogFootDialog.setView(money);

        dogFootDialog.setPositiveButton("확인", listener);
        dogFootDialog.setNegativeButton("취소", cancelListener);
        dogFootDialog.setColor(activity.getResources().getColor(R.color.dogFoot_yellow, activity.getTheme()));
        dogFootDialog.showDialog();
    }




    private void reject() {
        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).CompensationReject(id)
                .enqueue(MainRetrofitTool.getCallback(new CompensationRejectResponseCallback()));
        finish();

    }

    @Override
    public void dogFootEntityUpdated() {

    }

    private void payCompensation(String value) {
        CompensationPayRequest request = new CompensationPayRequest();
        request.setAccident_id(Long.parseLong(dataset.get(DogFootEntity.EDogFootData.ID)));
        request.setCompensation_amount(Long.parseLong(value));
        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).CompensationPay(request)
                .enqueue(MainRetrofitTool.getCallback(new payCompensationResponseCallback()));
        finish();

    }

    private void getDetail() {
        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).getCompensationContractDetail(id)
                .enqueue(MainRetrofitTool.getCallback(new CompensationContractDetailResponseCallback()));


    }

    private void getImage() {
        Glide.with(getApplicationContext()).load(URL+"image/"+fileName).into(this.imageView);
    }


    private class CompensationContractDetailResponseCallback implements MainRetrofitCallback<CompensationContractDetailResponse<List<Files>>>{
        @Override
        public void onSuccessResponse(Response<CompensationContractDetailResponse<List<Files>>> response) {
            fileName=response.body().getFiles().get(0).getFilename();
        }

        @Override
        public void onFailResponse(Response<CompensationContractDetailResponse<List<Files>>> response) {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.d("디버그", jObjError.getString("error-message"));
            } catch (Exception e) {
                Log.d("디버그", e.getMessage());
            }
        }

        @Override
        public void onConnectionFail(Throwable t) {
            Log.d("디버그", t.getMessage());
        }
    }


    private class payCompensationResponseCallback implements MainRetrofitCallback<CompensationPayResponse> {
        @Override
        public void onSuccessResponse(Response<CompensationPayResponse> response) {
            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailResponse(Response<CompensationPayResponse> response) {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.d("디버그", jObjError.getString("error-message"));
            } catch (Exception e) {
                Log.d("디버그", e.getMessage());
            }
        }

        @Override
        public void onConnectionFail(Throwable t) {
            Log.d("디버그", t.getMessage());
        }
    }

    private class CompensationRejectResponseCallback implements MainRetrofitCallback<CompensationRejectResponse>{
        @Override
        public void onSuccessResponse(Response<CompensationRejectResponse> response) {
            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailResponse(Response<CompensationRejectResponse> response) {
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.d("디버그", jObjError.getString("error-message"));
            } catch (Exception e) {
                Log.d("디버그", e.getMessage());
            }
        }

        @Override
        public void onConnectionFail(Throwable t) {
            Log.d("디버그", t.getMessage());
        }
    }
}
