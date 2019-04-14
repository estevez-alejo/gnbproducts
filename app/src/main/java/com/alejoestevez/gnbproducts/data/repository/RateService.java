package com.alejoestevez.gnbproducts.data.repository;

import com.alejoestevez.gnbproducts.data.model.Rate;
import com.alejoestevez.gnbproducts.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RateService {
    String URL = Constants.Companion.getAPI_URL();

    @GET("rates")
    Call<List<Rate>> getRates();
}
