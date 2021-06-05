package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.activity.DogFootViewModelActivity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model.ImageUploadResponse;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitCallback;
import com.dogfoot.insurancesystemapp.mainCrossDomain.tech.retrofit.MainRetrofitTool;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class SalesCompensationViewActivity extends DogFootViewModelActivity {


    private ImageView imageView;
    private Button approveButton, rejectButton;
    private String filePath;


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
        this.approveButton.setOnClickListener(v -> approve());
        this.rejectButton.setOnClickListener(v -> reject());



    }

    private void approve() {

    }





    private void reject() {

    }

    @Override
    public void dogFootEntityUpdated() {

    }

    private void getInfo(String imgPath) {
        File file = new File(imgPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
        RetrofitTool.getAPIWithAuthorizationToken(dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION)).uploadImage(uploadFile,Long.parseLong(dataset.get(DogFootEntity.EDogFootData.ID)))
                .enqueue(MainRetrofitTool.getCallback(new UploadImageRequestCallback()));


    }

    private class UploadImageRequestCallback implements MainRetrofitCallback<ImageUploadResponse> {
        @Override
        public void onSuccessResponse(Response<ImageUploadResponse> response) {
            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_LONG).show();
            finish();
        }

        @Override
        public void onFailResponse(Response<ImageUploadResponse> response) {
            Log.d("디버그",dataset.get(DogFootEntity.EDogFootData.AUTHORIZATION));
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Toast.makeText(getApplicationContext(), jObjError.getString("error-message"), Toast.LENGTH_LONG).show();
            } catch (Exception e) { Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show(); }

        }

        @Override
        public void onConnectionFail(Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
