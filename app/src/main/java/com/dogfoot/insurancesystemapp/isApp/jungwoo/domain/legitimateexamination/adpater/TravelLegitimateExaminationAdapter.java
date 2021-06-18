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
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.TravelInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view.TravelInsuranceApplicationFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model.TravelLegitimateExaminationResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.view.LegitimateExaminationTravelDetailedFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelLegitimateExaminationAdapter extends RecyclerView.Adapter<TravelLegitimateExaminationAdapter.CustomViewHolder>{

    private Vector<TravelLegitimateExaminationResponse> travelItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;


    public TravelLegitimateExaminationAdapter(Context context, FragmentActivity fragmentContext) {

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
    public void onBindViewHolder(@NonNull final TravelLegitimateExaminationAdapter.CustomViewHolder holder, int position) { // 추가될때 이 메서드가 실행된다.
        holder.tv_contractId.setText(String.valueOf(travelItems.get(position).getId()));
        holder.tv_contractName.setText(travelItems.get(position).getInsurance_name());
        holder.tv_contractUserName.setText(travelItems.get(position).getUser_name());
        holder.itemView.setTag(position);

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
            bundle.putString("strId", String.valueOf(travelItems.get(position).getId()));
            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LegitimateExaminationTravelDetailedFragment legitimateExaminationTravelDetailedFragment = LegitimateExaminationTravelDetailedFragment.newInstance();
            legitimateExaminationTravelDetailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_main, legitimateExaminationTravelDetailedFragment).commit();


        }
    }

    @Override
    public int getItemCount() {
        return (null != travelItems ? travelItems.size() : 0);
    }

    public void remove(int position) {
        try {
            travelItems.remove(position);
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

    public void addTravelItems(Vector<TravelLegitimateExaminationResponse> travelItems){
        this.travelItems = travelItems;
    }
}
