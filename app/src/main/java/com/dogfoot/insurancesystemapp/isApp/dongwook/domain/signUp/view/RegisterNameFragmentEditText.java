package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;


import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.LoadingDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.AbstractFragment_1EditText;

import org.json.JSONObject;

import retrofit2.Response;

public class RegisterNameFragmentEditText extends AbstractFragment_1EditText {
    boolean valueInput = false;
    // Component
        // View
        private LoadingDialog loadingDialog;

    /**
     * System Life Cycle Callback
     */
    @Override
    protected void initializeView() {
        super.initializeView();
        this.mainTextView.setText(getString(R.string.register_name_maintext));
        this.subTextView.setVisibility(View.INVISIBLE);
        this.editText.setHint(getString(R.string.register_name_hint));
        this.button.setText(getString(R.string.button_ok));
    }

    @Override
    protected void dogFootEntityUpdated() {
        super.dogFootEntityUpdated();
        if(valueInput){this.navigateTo(R.id.action_register3Fragment_to_register4Fragment);}
    }



    /**
     * Callback
     */
    @Override
    protected void buttonClicked() {
        this.dataset.put(DogFootEntity.EDogFootData.NAME, this.editText.getText().toString());
        this.valueInput=true;
        this.save();
    }
}