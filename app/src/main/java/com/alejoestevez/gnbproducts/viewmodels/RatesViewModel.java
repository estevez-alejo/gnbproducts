package com.alejoestevez.gnbproducts.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.alejoestevez.gnbproducts.data.model.Rate;
import com.alejoestevez.gnbproducts.data.repository.RatesRepository;

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

    public LiveData<List<Rate>> getNewRates() {
        ratesLiveData = RatesRepository.getInstance().getRates();
        return ratesLiveData;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new RatesViewModel(application);
        }
    }
}
