package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.adpater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyThirdFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.CarInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view.CarInsuranceApplicationFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.CarLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.view.LegitimateExaminationCarDetailedFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarLegitimateExaminationAdapter extends RecyclerView.Adapter<CarLegitimateExaminationAdapter.CustomViewHolder>{

    private Vector<CarLegitimateExaminationResponse> carItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;

    public CarLegitimateExaminationAdapter(Context context, FragmentActivity fragmentContext) {

        this.context = context;
        this.fragmentContext = fragmentContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_legitimate_examination, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarLegitimateExaminationAdapter.CustomViewHolder holder, int position) { // ???????????? ??? ???????????? ????????????.
        holder.tv_contractId.setText(String.valueOf(carItems.get(position).getId()));
        holder.tv_contractName.setText(carItems.get(position).getInsurance_name());
        holder.tv_contractUserName.setText(carItems.get(position).getUser_name());
        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ????????? ????????? ?????????
                doubleClickCheck(position);
            }

        });

    }

    private void doubleClickCheck(int position) {
        if (System.currentTimeMillis() > btnPressTime + 1000) {
            btnPressTime = System.currentTimeMillis();
            Toast.makeText(context.getApplicationContext(), "?????? ??? ???????????? ????????? ??? ??? ????????????.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= btnPressTime + 1000) {

            Bundle bundle = new Bundle();
            bundle.putString("strId", Long.toString(carItems.get(position).getId()));

            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LegitimateExaminationCarDetailedFragment legitimateExaminationCarDetailedFragment = LegitimateExaminationCarDetailedFragment.newInstance();
            legitimateExaminationCarDetailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_main, legitimateExaminationCarDetailedFragment).commit();



        }
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

        TextView tv_contractId;
        TextView tv_contractName;
        TextView tv_contractUserName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_contractId = itemView.findViewById(R.id.tv_contractId);
            this.tv_contractName = itemView.findViewById(R.id.tv_contractName);
            this.tv_contractUserName = itemView.findViewById(R.id.tv_contractUserName);
        }
    }

    public void addCarItems(Vector<CarLegitimateExaminationResponse> carItems){
        this.carItems = carItems;
    }
}
