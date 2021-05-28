package com.dogfoot.insurancesystemapp.isApp.jungwoo.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dogfoot.insurancesystemapp.databinding.ActivityIntroBinding;
import com.dogfoot.insurancesystemapp.isApp.MainActivity;
import com.dogfoot.insurancesystemapp.R;
public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityIntroBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        init();
        IntroThread introThread = new IntroThread(handler);
        introThread.start();
    }

    private void init() {
        mBinding.tvIntroIcon.setImageResource(R.drawable.dogfoot_icon);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };

}
