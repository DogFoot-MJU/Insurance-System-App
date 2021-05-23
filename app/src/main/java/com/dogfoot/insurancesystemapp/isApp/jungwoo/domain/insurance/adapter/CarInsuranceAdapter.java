package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.databinding.FragmentRegistrationCapacityPolicyThirdBinding;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyThirdFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceDetailedFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view.CarInsuranceApplicationFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarInsuranceAdapter extends RecyclerView.Adapter<CarInsuranceAdapter.CustomViewHolder>{

    private Vector<CarInsuranceResponse> carItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;
    private boolean capacityPolicy;
    private boolean insuranceApplication;

    public CarInsuranceAdapter(Context context, FragmentActivity fragmentContext, boolean capacityPolicy, boolean insuranceApplication) {
        this.carItems = new Vector<>();
        this.context = context;
        this.fragmentContext = fragmentContext;
        this.capacityPolicy = capacityPolicy;
        this.insuranceApplication = insuranceApplication;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_insurance, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarInsuranceAdapter.CustomViewHolder holder, int position) { // 추가될때 이 메서드가 실행된다.
        holder.tv_insuranceId.setText(String.valueOf(carItems.get(position).getId()));
        holder.tv_insuranceName.setText(carItems.get(position).getName());
        holder.itemView.setTag(position);

        holder.tv_insuranceDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("확인", Integer.toString(position));
                deleteDialog(position);
            }
        });
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이디 서버에 전해줌
                doubleClickCheck(position);
//                arrayList.get(position).getInsuranceId();
            }

        });

    }

    private void doubleClickCheck(int position) {
        if (System.currentTimeMillis() > btnPressTime + 1000) {
            btnPressTime = System.currentTimeMillis();
            Toast.makeText(context.getApplicationContext(), "한번 더 터치하면 자세히 볼 수 있습니다.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= btnPressTime + 1000) {


            Bundle bundle = new Bundle();
            bundle.putString("strId", Long.toString(carItems.get(position).getId()));
            bundle.putString("strInsuranceName", carItems.get(position).getName());
            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if(capacityPolicy == true){
                RegistrationCapacityPolicyThirdFragment registrationCapacityPolicyThirdFragment = RegistrationCapacityPolicyThirdFragment.newInstance();
                registrationCapacityPolicyThirdFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fl_main, registrationCapacityPolicyThirdFragment).commit();
            } else if(insuranceApplication == true){
                CarInsuranceApplicationFragment carInsuranceApplicationFragment = CarInsuranceApplicationFragment.newInstance();
                carInsuranceApplicationFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fl_main, carInsuranceApplicationFragment).commit();
            }


        }
    }


    private void deleteDialog(int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("삭제");
        builder.setMessage("해당 항목을 삭제하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // db에서 제거
                        removeByDB(carItems.get(position).getId());
                        // view에서 제거
                        remove(position);
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    private void removeByDB(Long id) {
        Constant constant = Constant.getInstance();
        //String token =

//        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
//        RetrofitTool.getAPIWithAuthorizationToken(token).deleteCarInsuracne(id).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(fragmentContext, "삭제를 완료했습니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (null != carItems ? carItems.size() : 0);
    }

    public void remove(int position){
        try {
            carItems.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_insuranceId;
        TextView tv_insuranceName;
        ImageButton tv_insuranceDelete;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_insuranceId = itemView.findViewById(R.id.tv_insuranceId);
            this.tv_insuranceName = itemView.findViewById(R.id.tv_insuranceName);
            this.tv_insuranceDelete = itemView.findViewById(R.id.tv_insuranceDelete);
        }
    }

    public void addCarItems(Vector<CarInsuranceResponse> carItems){
        this.carItems = carItems;
    }
}
