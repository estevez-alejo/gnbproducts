package com.alejoestevez.gnbproducts.view.ui.flows;

import android.content.Context;
import android.os.Bundle;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.utilities.Constants;
import com.alejoestevez.gnbproducts.utilities.IntentUtils;
import com.alejoestevez.gnbproducts.view.ui.fragments.ProductListFragment;

import androidx.appcompat.app.AppCompatActivity;

public class FlowProduct extends FlowBase {

    public static void goToProductList(AppCompatActivity activity, int orientation) {
        swapFragment(activity, new ProductListFragment().newInstance(), R.id.fragment_container, orientation);
    }

    public static void goToProductDetail(Context context, Product product) {
        Bundle params = new Bundle();
        params.putSerializable(Constants.Companion.getARG_PRODUCT(), product);
        IntentUtils.goToProductActivity(context, params);
    }
}
