package com.alejoestevez.gnbproducts.data.repository;

import com.alejoestevez.gnbproducts.data.model.Transaction;
import com.alejoestevez.gnbproducts.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface TransactionService {
    String URL = Constants.Companion.getAPI_URL();

    @GET("transactions")
    Call<List<Transaction>> getTransactions();
}
