package com.alejoestevez.gnbproducts.view.callback;

import android.view.View;

import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.view.ui.flows.FlowProduct;

public class OnClickCallback {
	public void onClick(View view, Product product) {
        FlowProduct.goToProductDetail(view.getContext(),product);
    }
}
