package com.alejoestevez.gnbproducts.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.alejoestevez.gnbproducts.data.model.Rate;
import com.alejoestevez.gnbproducts.data.repository.RatesRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RatesViewModel extends AndroidViewModel {
    private LiveData<List<Rate>> ratesLiveData;

    public RatesViewModel(@NonNull Application application) {
        super(application);
        ratesLiveData = RatesRepository.getInstance().getRates();
    }

    public LiveData<List<Rate>> getObservableProject() {
        return ratesLiveData;
    }

}
