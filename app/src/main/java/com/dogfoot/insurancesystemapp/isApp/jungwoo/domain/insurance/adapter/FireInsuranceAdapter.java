
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.view.RegistrationCapacityPolicyThirdFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.FireDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceDetailedFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignFireInsuranceDetailedFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model.FireInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view.DriverInsuranceApplicationFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.view.FireInsuranceApplicationFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FireInsuranceAdapter extends RecyclerView.Adapter<FireInsuranceAdapter.CustomViewHolder>{

    private Vector<FireInsuranceResponse> fireItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;
    private boolean capacityPolicy;
    private boolean insuranceApplication;

    public FireInsuranceAdapter(Context context, FragmentActivity fragmentContext, boolean capacityPolicy, boolean insuranceApplication) {
        this.fireItems = new Vector<>();
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
    public void onBindViewHolder(@NonNull final FireInsuranceAdapter.CustomViewHolder holder, int position) { // ???????????? ??? ???????????? ????????????.
        holder.tv_insuranceId.setText(String.valueOf(fireItems.get(position).getId()));
        holder.tv_insuranceName.setText(fireItems.get(position).getName());
        holder.itemView.setTag(position);

        holder.tv_insuranceDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeByDB(fireItems.get(position).getId());
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
            bundle.putString("strId", String.valueOf(fireItems.get(position).getId()));
            bundle.putString("strInsuranceName", fireItems.get(position).getName());
            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(capacityPolicy == true){
                RegistrationCapacityPolicyThirdFragment registrationCapacityPolicyThirdFragment = RegistrationCapacityPolicyThirdFragment.newInstance();
                registrationCapacityPolicyThirdFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fl_main, registrationCapacityPolicyThirdFragment).commit();
            } else if(insuranceApplication == true){
                FireInsuranceApplicationFragment fireInsuranceApplicationFragment = FireInsuranceApplicationFragment.newInstance();
                fireInsuranceApplicationFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fl_main, fireInsuranceApplicationFragment).commit();
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
                        removeByDB(fireItems.get(position).getId());

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
        RetrofitTool.getAPIWithAuthorizationToken(token).deleteFireInsurance(id).enqueue(new Callback<Void>() {
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
        return (null != fireItems ? fireItems.size() : 0);
    }

    public void remove(int position){
        try {
            fireItems.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_insuranceId;
        TextView tv_insuranceName;
        TextView tv_insuranceDelete;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_insuranceId = itemView.findViewById(R.id.tv_insuranceId);
            this.tv_insuranceName = itemView.findViewById(R.id.tv_insuranceName);
            this.tv_insuranceDelete = itemView.findViewById(R.id.tv_insuranceDelete);
        }
    }

    public void addFireItems(Vector<FireInsuranceResponse> fireItems){
        this.fireItems = fireItems;
    }
}
