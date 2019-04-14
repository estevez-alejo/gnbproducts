package com.alejoestevez.gnbproducts.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.databinding.ProductListItemBinding;
import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.view.callback.OnClickCallback;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<? extends Product> productList = new ArrayList<>();

    public void setProductList(final List<? extends Product> products) {
        if (!productList.isEmpty()) {
            productList.clear();
        }
        this.productList = products;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.product_list_item,
                        parent, false);

        binding.setCallback(new OnClickCallback());

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.binding.setProduct(productList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        final ProductListItemBinding binding;

        public ProductViewHolder(ProductListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
