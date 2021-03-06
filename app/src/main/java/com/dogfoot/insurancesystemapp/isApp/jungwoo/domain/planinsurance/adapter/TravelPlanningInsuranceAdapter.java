package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.adapter;

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
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignFireInsuranceFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignTravelInsuranceFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.DriverPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.FirePlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.TravelPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceDetailedFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelPlanningInsuranceAdapter extends RecyclerView.Adapter<TravelPlanningInsuranceAdapter.CustomViewHolder>{

    private Vector<TravelPlanInsuranceResponse> travelItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;
    private boolean forDesign = false;

    public TravelPlanningInsuranceAdapter(Context context, FragmentActivity fragmentContext, boolean forDesign) {
        this.travelItems = new Vector<>();
        this.context = context;
        this.fragmentContext = fragmentContext;
        this.forDesign = forDesign;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_plan_insurance, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TravelPlanningInsuranceAdapter.CustomViewHolder holder, int position) { // ???????????? ??? ???????????? ????????????.
        holder.tv_insuranceId.setText(String.valueOf(travelItems.get(position).getId()));
        holder.tv_InsuranceName.setText(travelItems.get(position).getName());
        holder.tv_insurancePayment.setText(String.valueOf(travelItems.get(position).getPayment()));
        holder.itemView.setTag(position);

        holder.ib_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("??????", Integer.toString(position));
                deleteDialog(position);
            }
        });
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ????????? ????????? ?????????
                doubleClickCheck(position);
//                arrayList.get(position).getInsuranceId();
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
            bundle.putString("strId", String.valueOf(travelItems.get(position).getId()));
            bundle.putString("strName", travelItems.get(position).getName());
            bundle.putString("strPayment", String.valueOf(travelItems.get(position).getPayment()));
            bundle.putString("strState", String.valueOf(travelItems.get(position).getState()));

            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(forDesign == false) {
                PlanInsuranceDetailedFragment planInsuranceDetailedFragment = PlanInsuranceDetailedFragment.newInstance();
                planInsuranceDetailedFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fl_main, planInsuranceDetailedFragment).commit();
            } else if(forDesign ==true) {
                DesignTravelInsuranceFragment designTravelInsuranceFragment = DesignTravelInsuranceFragment.newInstance();
                designTravelInsuranceFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fl_main, designTravelInsuranceFragment).commit();
            }

        }
    }


    private void deleteDialog(int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("??????");
        builder.setMessage("?????? ????????? ?????????????????????????");
        builder.setPositiveButton("???",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // db?????? ??????
                        removeByDB(travelItems.get(position).getId());

                        // view?????? ??????
                        remove(position);
                    }
                });
        builder.setNegativeButton("?????????",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    private void removeByDB(Long id) {
        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token).deleteTravelInsuracne(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(fragmentContext, "????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
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

        TextView tv_insuranceId;
        TextView tv_InsuranceName;
        TextView tv_insurancePayment;
        TextView ib_clear;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_insuranceId = itemView.findViewById(R.id.tv_planInsuranceId);
            this.tv_InsuranceName = itemView.findViewById(R.id.tv_planInsuranceName);
            this.tv_insurancePayment = itemView.findViewById(R.id.tv_planInsurancePayment);
            this.ib_clear = itemView.findViewById(R.id.ib_clear);
        }
    }

    public void addTravelItems(Vector<TravelPlanInsuranceResponse> travelItems){
        this.travelItems = travelItems;
    }
}
