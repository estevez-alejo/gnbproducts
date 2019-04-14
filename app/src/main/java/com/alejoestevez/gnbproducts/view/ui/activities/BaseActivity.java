package com.alejoestevez.gnbproducts.view.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void setActionBarTitle(String title) {
        if (validateActionBar()) {
            getSupportActionBar().setTitle(title);
        }
    }

    public boolean validateActionBar() {
        return (getSupportActionBar() != null);
    }
}
