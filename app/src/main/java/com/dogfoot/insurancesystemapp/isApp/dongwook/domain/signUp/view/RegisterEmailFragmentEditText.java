package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.signUp.view;

import android.util.Log;
import android.view.View;

import com.dogfoot.insurancesystemapp.R;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.model.DogFootEntity;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.dialog.DogFootDialog;
import com.dogfoot.insurancesystemapp.isApp.crossDomain.domain.view.fragment.AbstractFragment_1EditText;
import com.dogfoot.insurancesystemapp.isApp.dongwook.DongWookActivity;


public class RegisterEmailFragmentEditText extends AbstractFragment_1EditText {
    // Working Variable
    boolean valueInput = false;
    /**
     * System Life Cycle Callback
     */
    @Override
    protected void initializeView() {
        super.initializeView();
        this.mainTextView.setText(getString(R.string.register_email_maintext));
        this.subTextView.setVisibility(View.INVISIBLE);
        this.editText.setHint(getString(R.string.register_email_hint));
        this.button.setText(getString(R.string.button_ok));
    }
    @Override
    protected void dogFootEntityUpdated() {
        super.dogFootEntityUpdated();
        if(this.dataset.containsKey(DogFootEntity.EDogFootData.AUTHORIZATION)){ this.showLoginedDialog(); }
        if(valueInput){this.navigateTo(R.id.action_register1Fragment_to_register2Fragment);}
    }

    /**
     * Callback
     */
    @Override
    protected void buttonClicked() {
        this.dataset.put(DogFootEntity.EDogFootData.EMAIL, this.editText.getText().toString());
        this.valueInput=true;
        this.save();
    }

    /**
     * Method
     */
    private void showLoginedDialog(){
         DogFootDialog.simplerAlertDialog(this.getActivity(),
            R.string.login_already_signed_dialog, R.string.login_already_signed_content_dialog,
            (dialog, which) -> this.startActivity(DongWookActivity.class)
        );
    }
}