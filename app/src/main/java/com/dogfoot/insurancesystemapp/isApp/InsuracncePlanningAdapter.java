package com.dogfoot.insurancesystemapp.isApp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;

import java.util.ArrayList;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsuracncePlanningAdapter extends RecyclerView.Adapter<InsuracncePlanningAdapter.CustomViewHolder>{

    private ArrayList<InsurancePlanning> arrayList;

    @NonNull
    @Override
    public InsuracncePlanningAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_plan_insurance, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InsuracncePlanningAdapter.CustomViewHolder holder, int position) { // 추가될때 이 메서드가 실행된다.
        holder.tv_InsuranceName.setText(arrayList.get(position).getInsuranceName());
        holder.tv_InsuranceType.setText(arrayList.get(position).getInsuranceType());

        holder.itemView.setTag(position); // view의 재활용을 위해 사용한다.

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_InsuranceName;
        TextView tv_InsuranceType;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_InsuranceName = itemView.findViewById(R.id.tv_insuranceName);
            this.tv_InsuranceType = itemView.findViewById(R.id.tv_insuranceType);
        }
    }
}
