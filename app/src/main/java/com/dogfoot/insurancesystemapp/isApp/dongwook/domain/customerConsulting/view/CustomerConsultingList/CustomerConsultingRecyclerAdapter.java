package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingList;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model.CustomerConsultingListResponse;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.view.CustomerConsultingViewActivity;


import java.util.Vector;

import static com.dogfoot.insurancesystemapp.isApp.constants.Constant.CUSTOMER_CONSULTING__ACTIVITY_ID;

public class CustomerConsultingRecyclerAdapter extends RecyclerView.Adapter<CustomerConsultingViewHolder> {

    // Associate
        // ETC
        private Activity activity;
        private Vector<CustomerConsultingListResponse> items;

    // Constructor
    public CustomerConsultingRecyclerAdapter(Activity activity) {
        this.items = new Vector<>();
        this.activity=activity;
    }




    @Override
    public CustomerConsultingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_consulting, parent, false);

        return new CustomerConsultingViewHolder(view, v->this.startViewActivity((Long) v.getTag()), this.activity);
    }
    @Override
    public void onBindViewHolder(CustomerConsultingViewHolder holder, int position) {
        holder.setValue(items.get(position));
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void setItems(Vector<CustomerConsultingListResponse> items) { this.items=items; }
    private void startViewActivity(Long id) {

        Intent intent = new Intent(this.activity, CustomerConsultingViewActivity.class);
        intent.putExtra("viewId",id);




        this.activity.startActivity(intent);

    }
}
