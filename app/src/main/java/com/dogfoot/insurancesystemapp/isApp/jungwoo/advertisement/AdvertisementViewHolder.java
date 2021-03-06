package com.dogfoot.insurancesystemapp.isApp.jungwoo.advertisement;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;


public class AdvertisementViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public AdvertisementViewHolder(View itemView) {
        super(itemView);
        this.imageView=itemView.findViewById(R.id.mainAdvertisementItem_imageView);

    }

    public void setText(int position) {
        TextView tv = this.itemView.findViewById(R.id.textView);
        tv.setText("임시 광고 "+position);
    }

    public void setImage(Drawable drawable) {
        this.imageView.setClipToOutline(true);
        this.imageView.setImageDrawable(drawable);
    }
}
