package com.alejoestevez.gnbproducts.view.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.utilities.IntentUtils;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //final SplashActivity activity = this;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtils.goToMainActivity(SplashActivity.this);
            }
        }, 1500);
    }

}
