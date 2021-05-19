package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view;

import android.view.View;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.AbstractFragment_1EditText;
import com.dogfoot.insurancesystemapp.isApp.dongwook.DongWookActivity;


public class RegisterFinishedFragmentEditText extends AbstractFragment_1EditText {

    /**
     * System Life Cycle Callback
     */
    @Override
    protected void initializeView() {
        super.initializeView();
        this.mainTextView.setText(String.format(getString(R.string.welcome_start)+" "+this.dataset.get(DogFootEntity.EDogFootData.NAME)+getString(R.string.welcome_end)));
        this.subTextView.setText(getString(R.string.register_finish_subtext));
        this.editText.setVisibility(View.INVISIBLE);
        this.button.setText(getString(R.string.button_ok));
    }

    /**
     * Callback
     */
    @Override
    protected void buttonClicked() {
        this.startActivity(DongWookActivity.class);
    }
}