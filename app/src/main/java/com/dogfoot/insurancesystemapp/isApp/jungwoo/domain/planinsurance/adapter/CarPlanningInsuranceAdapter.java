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
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model.CarPlanInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.view.PlanInsuranceDetailedFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarPlanningInsuranceAdapter extends RecyclerView.Adapter<CarPlanningInsuranceAdapter.CustomViewHolder>{

    private Vector<CarPlanInsuranceResponse> carItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;
    private boolean forDesign = false;


    public CarPlanningInsuranceAdapter(Context context, FragmentActivity fragmentContext, boolean forDesign) {
        this.carItems = new Vector<>();
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
    public void onBindViewHolder(@NonNull final CarPlanningInsuranceAdapter.CustomViewHolder holder, int position) { // ???????????? ??? ???????????? ????????????.
        holder.tv_insuranceId.setText(Integer.toString(carItems.get(position).getId()));
        holder.tv_InsuranceName.setText(carItems.get(position).getName());
        holder.tv_insurancePayment.setText(carItems.get(position).getPayment());
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
                bundle.putString("strId", Integer.toString(carItems.get(position).getId()));
                bundle.putString("strName", carItems.get(position).getName());
                bundle.putString("strPayment", carItems.get(position).getPayment());
                bundle.putString("strState", carItems.get(position).getState());

                FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(forDesign == false) {
                    PlanInsuranceDetailedFragment planInsuranceDetailedFragment = PlanInsuranceDetailedFragment.newInstance();
                    planInsuranceDetailedFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fl_main, planInsuranceDetailedFragment).commit();
                } else if(forDesign ==true) {
                    DesignCarInsuranceFragment designCarInsuranceFragment = DesignCarInsuranceFragment.newInstance();
                    designCarInsuranceFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fl_main, designCarInsuranceFragment).commit();
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
                       removeByDB(carItems.get(position).getId());
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

    private void removeByDB(int id) {
        Constant constant = Constant.getInstance();
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token).deleteCarInsuracne(Long.valueOf(id)).enqueue(new Callback<Void>() {
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

    public void addCarItems(Vector<CarPlanInsuranceResponse> carItems){
        this.carItems = carItems;
    }
}
