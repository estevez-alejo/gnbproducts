package com.alejoestevez.gnbproducts.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alejoestevez.gnbproducts.data.model.Rate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatesRepository {
    private RateService rateService;
    private RateClient rateClient;
    private static RatesRepository rateRepository;

    private RatesRepository() {
        rateClient = RateClient.getInstance();
        rateService = rateClient.getRateService();
    }

    public synchronized static RatesRepository getInstance() {
        if (rateRepository == null) {
            if (rateRepository == null) {
                rateRepository = new RatesRepository();
            }
        }
        return rateRepository;
    }

    public LiveData<List<Rate>> getRates() {
        final MutableLiveData<List<Rate>> data = new MutableLiveData<>();
        rateService.getRates().enqueue(new Callback<List<Rate>>() {
            @Override
            public void onResponse(Call<List<Rate>> call, Response<List<Rate>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Rate>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
