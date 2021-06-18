package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.view.salesConsultingList;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model.SalesConsultingListResponse;


public class SalesConsultingViewHolder extends RecyclerView.ViewHolder {



    // Associate
        // View

        private TextView title, author,isAnswer,time;
        // ETC
        private Activity activity;


    // Constructor
    public SalesConsultingViewHolder(View itemView, View.OnClickListener onClickListener, Activity activity) {
        super(itemView);

        // Associate
        this.activity=activity;

        // Associate View

        this.title = itemView.findViewById(R.id.item_customer_consulting_title_input);
        this.author = itemView.findViewById(R.id.item_customer_consulting_author_input);
        this.isAnswer = itemView.findViewById(R.id.item_customer_consulting_isanswer_input);
        this.time = itemView.findViewById(R.id.item_customer_consulting_time_input);

        // Initialize View
        itemView.setOnClickListener(onClickListener);





    }

    public void setValue(SalesConsultingListResponse response,View.OnClickListener listener) {
        this.itemView.setTag(getAdapterPosition());
        this.itemView.setOnClickListener(listener);
        this.title.setText(response.getTitle());
        this.author.setText(response.getWriter());
        this.isAnswer.setText(response.getState());
        this.time.setText(response.getCreation_date());

    }
}