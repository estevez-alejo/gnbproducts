package com.alejoestevez.gnbproducts.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alejoestevez.gnbproducts.data.model.Product;
import com.alejoestevez.gnbproducts.data.model.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {
    private TransactionService transactionService;
    private TransactionClient transactionClient;
    private static TransactionRepository transactionRepository;

    MutableLiveData<List<Transaction>> allTransactions = new MutableLiveData<>();
    MutableLiveData<List<Product>> allProducts = new MutableLiveData<>();

    private TransactionRepository() {
        transactionClient = TransactionClient.getInstance();
        transactionService = transactionClient.getTransactionService();
    }

    public synchronized static TransactionRepository getInstance() {
        if (transactionRepository == null) {
            if (transactionRepository == null) {
                transactionRepository = new TransactionRepository();
            }
        }
        return transactionRepository;
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        transactionService.getTransactions().enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                allTransactions.setValue(response.body());
                if (allProducts == null) {
                    allProducts = new MutableLiveData<>();
                }

                List<Product> productList = new ArrayList<>();
                Iterator itTransactions = allTransactions.getValue().iterator();

                while (itTransactions.hasNext()) {
                    Transaction current = (Transaction) itTransactions.next();
                    Product product = new Product(current.sku);
                    int position = productList.indexOf(product);
                    if (position > -1) {
                        productList.get(position).transactions.add(current);
                    } else {
                        product.transactions.add(current);
                        productList.add(product);
                    }
                }
                allProducts.setValue(productList);
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                allTransactions.setValue(null);
            }
        });
        return allTransactions;
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

}
