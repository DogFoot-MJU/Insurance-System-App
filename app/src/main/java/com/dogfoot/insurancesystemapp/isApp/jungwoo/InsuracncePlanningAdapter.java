package com.dogfoot.insurancesystemapp.isApp.jungwoo;

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

import java.util.Vector;

public class InsuracncePlanningAdapter extends RecyclerView.Adapter<InsuracncePlanningAdapter.CustomViewHolder>{

    private Vector<InsurancePlanning> arrayList;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;


    public InsuracncePlanningAdapter(Vector<InsurancePlanning> arrayList, Context context, FragmentActivity fragmentContext) {
        this.arrayList = arrayList;
        this.context = context;
        this.fragmentContext = fragmentContext;
    }

    @NonNull
    @Override
    public InsuracncePlanningAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_plan_insurance, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InsuracncePlanningAdapter.CustomViewHolder holder, int position) { // 추가될때 이 메서드가 실행된다.
        Log.e("만들어질때숫자", Integer.toString(position));
        holder.tv_InsuranceName.setText(arrayList.get(position).getName());
        holder.tv_insurancePayment.setText(arrayList.get(position).getPayment());
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
            bundle.putString("strName", arrayList.get(position).getName());
            bundle.putString("strPayment", arrayList.get(position).getPayment());
            bundle.putString("strState", arrayList.get(position).getState());
            bundle.putString("strType", arrayList.get(position).getType());
            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            PlanInsuranceDetailedFragment planInsuranceDetailedFragment = PlanInsuranceDetailedFragment.newInstance();
            planInsuranceDetailedFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_main, planInsuranceDetailedFragment).commit();

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
                        arrayList.get(position).getId(); //이거 db에 전달해서 삭제

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
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_InsuranceName;
        TextView tv_insurancePayment;
        ImageButton ib_clear;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_InsuranceName = itemView.findViewById(R.id.tv_insuranceName);
            this.tv_insurancePayment = itemView.findViewById(R.id.tv_insurancePayment);
            this.ib_clear = itemView.findViewById(R.id.ib_clear);
        }
    }
}
