package com.dogfoot.insurancesystemapp.isApp.jungwoo.advertisement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.jungwoo.JungWoo;

public class AdvertisementViewPagerAdapter extends RecyclerView.Adapter<AdvertisementViewHolder> {

    private final JungWoo.EAdImage[] values;
    private final Context context;

    public AdvertisementViewPagerAdapter(JungWoo.EAdImage[] values, Context context) {
   this.values=values;
   this.context=context;
    }

    @Override
    public AdvertisementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_advertisement, parent, false);
        return new AdvertisementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdvertisementViewHolder holder, int position) {
//        holder.setText(position);
        holder.setImage(this.context.getDrawable(values[position].getImageId()));
    }

    @Override public int getItemCount() { return this.values.length; }
}