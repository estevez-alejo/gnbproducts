package com.alejoestevez.gnbproducts.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.data.model.Transaction;
import com.alejoestevez.gnbproducts.data.repository.TransactionRepository;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private LiveData<List<Transaction>> transactionsLiveData;
    private LiveData<List<Product>> productsLiveData;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        transactionsLiveData = TransactionRepository.getInstance().getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return transactionsLiveData;
    }

    public LiveData<List<Product>> getObservableProducts() {
        productsLiveData = TransactionRepository.getInstance().getAllProducts();
        return productsLiveData;
    }

}
