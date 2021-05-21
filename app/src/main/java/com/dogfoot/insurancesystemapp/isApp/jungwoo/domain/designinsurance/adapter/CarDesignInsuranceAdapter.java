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
import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.tech.RetrofitTool;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.authorizeinsurance.view.AuthorizeInsuranceFirstFragment;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model.CarDesignInsuranceResponse;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.view.DesignCarInsuranceDetailedFragment;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDesignInsuranceAdapter extends RecyclerView.Adapter<CarDesignInsuranceAdapter.CustomViewHolder>{

    private Vector<CarDesignInsuranceResponse> carItems;
    private Context context;
    private FragmentActivity fragmentContext;
    private long btnPressTime = 0;
    private static boolean authorize = false;
    private static boolean approve = false;

    public CarDesignInsuranceAdapter(Context context, FragmentActivity fragmentContext, boolean authorize, boolean approve) {
        this.carItems = new Vector<>();
        this.context = context;
        this.fragmentContext = fragmentContext;
        this.authorize = authorize;
        this.approve = approve;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("크리에이트뷰홀더", "크리에이트뷰홀더");
        if(DesignCarInsuranceDetailedFragment.getBackCheckAuthorize()==true){
            this.authorize = true;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_plan_insurance, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarDesignInsuranceAdapter.CustomViewHolder holder, int position) { // 추가될때 이 메서드가 실행된다.
        Log.e("바인드뷰홀더", "바인드뷰홀더");
        holder.tv_insuranceId.setText(Long.toString(carItems.get(position).getId()));
        holder.tv_InsuranceName.setText(carItems.get(position).getName());
        holder.tv_insurancePayment.setText(Long.toString(carItems.get(position).getPayment()));
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
            bundle.putString("strId", Long.toString(carItems.get(position).getId()));
            bundle.putString("strName", carItems.get(position).getName());
            bundle.putString("strPayment", Long.toString(carItems.get(position).getPayment()));
           bundle.putString("strState", String.valueOf(carItems.get(position).getState()));
            //bundle.putSerializable("strState", carItems.get(position).getState());
            bundle.putString("authorize", String.valueOf(authorize));
            bundle.putString("approve", String.valueOf(approve));

            FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DesignCarInsuranceDetailedFragment designCarInsuranceDetailedFragment = DesignCarInsuranceDetailedFragment.newInstance();
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
        String token = constant.getDataset().get(DogFootEntity.EDogFootData.AUTHORIZATION);
        RetrofitTool.getAPIWithAuthorizationToken(token).deleteCarInsuracne(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(fragmentContext, "삭제를 완료했습니다.", Toast.LENGTH_SHORT).show();
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
        ImageButton ib_clear;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_insuranceId = itemView.findViewById(R.id.tv_planInsuranceId);
            this.tv_InsuranceName = itemView.findViewById(R.id.tv_planInsuranceName);
            this.tv_insurancePayment = itemView.findViewById(R.id.tv_planInsurancePayment);
            this.ib_clear = itemView.findViewById(R.id.ib_clear);
        }
    }

    public void addCarItems(Vector<CarDesignInsuranceResponse> carItems){
        this.carItems = carItems;
    }
}
