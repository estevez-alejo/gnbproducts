package com.alejoestevez.gnbproducts.view.ui.activities;

import android.os.Bundle;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.utilities.Transition;
import com.alejoestevez.gnbproducts.view.ui.flows.FlowProduct;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FlowProduct.goToProductList(this, Transition.FROM_RIGHT_TO_LEFT.getId());
        }
    }

}
