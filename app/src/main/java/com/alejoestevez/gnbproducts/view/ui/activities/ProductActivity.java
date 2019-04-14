package com.alejoestevez.gnbproducts.view.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.databinding.ActivityProductDetailBinding;
import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.data.model.Rate;
import com.alejoestevez.gnbproducts.utilities.Constants;
import com.alejoestevez.gnbproducts.view.adapter.TransactionAdapter;
import com.alejoestevez.gnbproducts.viewmodels.Factory;
import com.alejoestevez.gnbproducts.viewmodels.RatesViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;

public class ProductActivity extends BaseActivity {

    private ActivityProductDetailBinding binding;
    private Product product;
    private RatesViewModel ratesViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setAdapter();
        setObserver();
        setToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        binding.setIsLoading(true);
        product = (Product) getIntent().getSerializableExtra(Constants.Companion.getARG_PRODUCT());
        binding.setProduct(product);
    }

    private void setToolbar() {
        setActionBarTitle(product.sku);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setAdapter() {
        TransactionAdapter transactionAdapter = new TransactionAdapter();
        binding.transactionList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.transactionList.setAdapter(transactionAdapter);
        transactionAdapter.setProjectList(product.transactions);
    }

    private void setObserver() {
        Factory ratesFactory = new Factory(
                getApplication());

        ratesViewModel = ViewModelProviders.of(this, ratesFactory)
                .get(RatesViewModel.class);

        observeRatesViewModel(ratesViewModel);
    }

    private void observeRatesViewModel(RatesViewModel ratesViewModel) {
        ratesViewModel.getObservableProject().observe(this, new Observer<List<Rate>>() {
            @Override
            public void onChanged(@Nullable List<Rate> rates) {
                if (rates != null) {
                    binding.setIsLoading(false);
                    product.setRates(rates);
                    binding.setTotal(getString(R.string.transaction_total, product.getTotalAmount()));
                    binding.setTotaltransactions(getString(R.string.transactions_total, product.getCountTransactions()));
                }
            }
        });
    }

}