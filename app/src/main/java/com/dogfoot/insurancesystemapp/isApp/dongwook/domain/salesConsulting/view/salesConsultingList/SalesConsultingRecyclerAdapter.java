package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view.salesConsultingList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingListResponse;

import java.util.Vector;

public class SalesConsultingRecyclerAdapter extends RecyclerView.Adapter<SalesConsultingViewHolder> {

    // Associate
        // ETC
        private Activity activity;
        private Vector<SalesConsultingListResponse> items;
        private View.OnClickListener onClickListener;

    // Constructor
    public SalesConsultingRecyclerAdapter(Activity activity,View.OnClickListener listener) {
        this.items = new Vector<>();
        this.activity=activity;
        this.onClickListener=listener;
    }




    @Override
    public SalesConsultingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_consulting, parent, false);

        return new SalesConsultingViewHolder(view, v->this.startViewActivity((Long) v.getTag()), this.activity);
    }
    @Override
    public void onBindViewHolder(SalesConsultingViewHolder holder, int position) {
        holder.setValue(items.get(position),this.onClickListener);
    }
    @Override
    public int getItemCount() { return items.size(); }

    public void setItems(Vector<SalesConsultingListResponse> items) { this.items=items; }
    private void startViewActivity(Long id) {


    }
}
