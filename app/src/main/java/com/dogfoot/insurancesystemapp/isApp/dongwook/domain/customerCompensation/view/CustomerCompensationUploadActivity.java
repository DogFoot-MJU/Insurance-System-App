package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.view;

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

public class CustomerCompensationUploadActivity extends DogFootViewModelActivity {

    boolean isOnlyImageAllowed = true;
    private ImageView imageView;
    private Button uploadButton, addImageButton;
    private String filePath;
    private static final int PICK_PHOTO = 1958;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_compensation_upload);

    }

    @Override
    protected void associateView() {
        imageView = findViewById(R.id.imageUpload_imageView);
        uploadButton = findViewById(R.id.imageUpload_uploadButton);
        addImageButton = findViewById(R.id.imageUpload_addImageButton);

    }


    @Override
    protected void initializeView() {
        this.uploadButton.setEnabled(false);
        this.addImageButton.setOnClickListener(v -> select());
        this.uploadButton.setOnClickListener(v -> upload());
        if(filePath!=null){
            uploadButton.setEnabled(true);
        }


    }

    private void select() {
        Intent intent;

        if (isOnlyImageAllowed) {
            // only image can be selected
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        } else {
            // any type of files including image can be selected
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("file/*");
        }

        startActivityForResult(intent, PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            filePath = getPath(imageUri);
            imageView.setImageURI(imageUri);
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void upload() {
        imageUpload(this.filePath);
    }

    @Override
    public void dogFootEntityUpdated() {

    }

    private void imageUpload(String imgPath) {
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
