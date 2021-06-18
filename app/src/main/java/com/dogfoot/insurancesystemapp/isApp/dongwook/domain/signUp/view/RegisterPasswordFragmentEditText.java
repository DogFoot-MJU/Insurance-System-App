package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.AbstractFragment_1EditText;


public class RegisterPasswordFragmentEditText extends AbstractFragment_1EditText {
    boolean valueInput = false;
    /**
     * System Life Cycle Callback
     */
    @Override
    protected void initializeView() {
        super.initializeView();
        this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.mainTextView.setText(getString(R.string.register_password_maintext));
        this.subTextView.setVisibility(View.INVISIBLE);
        this.editText.setHint(getString(R.string.register_password_hint));
        this.button.setText(getString(R.string.button_ok));
    }
    @Override
    protected void dogFootEntityUpdated() {
        super.dogFootEntityUpdated();
        if(valueInput){this.navigateTo(R.id.action_register2Fragment_to_register3Fragment);}
    }
    /**
     * Callback
     */
    @Override
    protected void buttonClicked() {
        this.dataset.put(DogFootEntity.EDogFootData.PASSWORD, this.editText.getText().toString());
        this.valueInput=true;
        this.save();
    }
}