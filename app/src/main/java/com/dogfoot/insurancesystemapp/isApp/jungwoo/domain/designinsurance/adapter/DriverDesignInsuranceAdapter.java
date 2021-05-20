package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.adapter;

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
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.DriverDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceDetailedFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignDriverInsuranceDetailedFragment;

import java.util.Vector;

public class DriverDesignInsuranceAdapter extends RecyclerView.Adapter<DriverDesignInsuranceAdapter.CustomViewHolder>{

    Vector<DriverDesignInsuranceResponse> driverItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;


    public DriverDesignInsuranceAdapter(Context context, FragmentActivity fragmentContext) {
        this.driverItems = new Vector<>();
        this.context = context;
        this.fragmentContext = fragmentContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_plan_insurance, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DriverDesignInsuranceAdapter.CustomViewHolder holder, int position) { // 추가될때 이 메서드가 실행된다.
        holder.tv_insuranceId.setText(Integer.toString(driverItems.get(position).getId()));
        holder.tv_InsuranceName.setText(driverItems.get(position).getName());
        holder.tv_insurancePayment.setText(driverItems.get(position).getPayment());
        holder.itemView.setTag(position);

        holder.ib_clear.setOnClickListener(new View.OnClickListener() {
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
            bundle.putString("strId", Integer.toString(driverItems.get(position).getId()));
            bundle.putString("strName", driverItems.get(position).getName());
            bundle.putString("strPayment", driverItems.get(position).getPayment());
            bundle.putString("strState", driverItems.get(position).getState());
            bundle.putString("strAcquisition", driverItems.get(position).getDate_of_license_acquisition());
            bundle.putString("strDriver", driverItems.get(position).getDriver_license());
            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            DesignDriverInsuranceDetailedFragment designCarInsuranceDetailedFragment = DesignDriverInsuranceDetailedFragment.newInstance();
            designCarInsuranceDetailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_main, designCarInsuranceDetailedFragment).commit();

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
                        driverItems.get(position).getId(); //이거 db에 전달해서 삭제

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

    @Override
    public int getItemCount() {
        return (null != driverItems ? driverItems.size() : 0);
    }

    public void remove(int position){
        try {
            driverItems.remove(position);
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
        ImageButton ib_clear;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_insuranceId = itemView.findViewById(R.id.tv_planInsuranceId);
            this.tv_InsuranceName = itemView.findViewById(R.id.tv_planInsuranceName);
            this.tv_insurancePayment = itemView.findViewById(R.id.tv_planInsurancePayment);
            this.ib_clear = itemView.findViewById(R.id.ib_clear);
        }
    }

    public void addDriverItems(Vector<DriverDesignInsuranceResponse> driverItems){
        this.driverItems = driverItems;
    }
}
