package com.alejoestevez.gnbproducts.view.ui.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.databinding.FragmentProductsListBinding;
import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.view.adapter.ProductAdapter;
import com.alejoestevez.gnbproducts.viewmodels.TransactionViewModel;

import java.util.List;

public class ProductListFragment extends Fragment {
    public static final String TAG = "ProductListFragment";
    private ProductAdapter productAdapter;
    private FragmentProductsListBinding binding;
    private TransactionViewModel transactionViewModel;

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products_list, container, false);

        productAdapter = new ProductAdapter();
        binding.productList.setAdapter(productAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TransactionViewModel.Factory transactionFactory = new TransactionViewModel.Factory(
                getActivity().getApplication());

        transactionViewModel = ViewModelProviders.of(this, transactionFactory)
                .get(TransactionViewModel.class);
        binding.setIsLoading(true);
        observeProductsViewModel(transactionViewModel);
    }

    private void observeProductsViewModel(TransactionViewModel transactionViewModel) {
        transactionViewModel.getObservableProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                if (products != null) {
                    binding.setIsLoading(false);
                    productAdapter.setProductList(products);
                }
            }
        });
    }

}
