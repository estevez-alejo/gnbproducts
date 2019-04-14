package com.alejoestevez.gnbproducts.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class Factory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    public Factory(@NonNull Application application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return modelClass.getConstructor(Application.class).newInstance(application);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
