package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.adapter;

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
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model.CapacityPolicyResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyDetailedFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter.CarDesignInsuranceAdapter;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceDetailedFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapacityPolicyAdapter extends RecyclerView.Adapter<CapacityPolicyAdapter.CustomViewHolder>{

    private Vector<CapacityPolicyResponse> capacityPolicyList;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;

    public CapacityPolicyAdapter(Context context, FragmentActivity fragmentContext) {
        this.context = context;
        this.fragmentContext = fragmentContext;
    }

    @NonNull
    @Override
    public CapacityPolicyAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_capacity_policy, parent, false);
        CapacityPolicyAdapter.CustomViewHolder holder = new CapacityPolicyAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CapacityPolicyAdapter.CustomViewHolder holder, int position) { // ???????????? ??? ???????????? ????????????.
        holder.tv_capacityPolicyId.setText(String.valueOf(capacityPolicyList.get(position).getId()));
        holder.tv_capacityPolicyName.setText(capacityPolicyList.get(position).getName());
        holder.tv_capacityPolicyInsuranceName.setText(capacityPolicyList.get(position).getInsurance_name());
        holder.itemView.setTag(position);

        holder.ib_capacityPolicyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog(position);
            }
        });

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
            bundle.putString("strId", String.valueOf(capacityPolicyList.get(position).getId()));
            bundle.putString("strPolicyName", capacityPolicyList.get(position).getName());
            bundle.putString("strInsuranceName", capacityPolicyList.get(position).getInsurance_name());

            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RegistrationCapacityPolicyDetailedFragment registrationCapacityPolicyDetailedFragment = RegistrationCapacityPolicyDetailedFragment.newInstance();
            registrationCapacityPolicyDetailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_main, registrationCapacityPolicyDetailedFragment).commit();

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
                        removeByDB(capacityPolicyList.get(position).getId());
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
        RetrofitTool.getAPIWithAuthorizationToken(token).deleteCapacityPolicy(id).enqueue(new Callback<Void>() {
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
        return (null != capacityPolicyList ? capacityPolicyList.size() : 0);
    }

    public void remove(int position){
        try {
            capacityPolicyList.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public void addCarItems(Vector<CapacityPolicyResponse> capacityPolicyList){
        this.capacityPolicyList = capacityPolicyList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_capacityPolicyId;
        TextView tv_capacityPolicyName;
        TextView tv_capacityPolicyInsuranceName;
        TextView ib_capacityPolicyDelete;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_capacityPolicyId = itemView.findViewById(R.id.tv_capacityPolicyId);
            this.tv_capacityPolicyName = itemView.findViewById(R.id.tv_capacityPolicyName);
            this.tv_capacityPolicyInsuranceName = itemView.findViewById(R.id.tv_capacityPolicyInsuranceName);
            this.ib_capacityPolicyDelete = itemView.findViewById(R.id.ib_capacityPolicyDelete);
        }
    }
}
