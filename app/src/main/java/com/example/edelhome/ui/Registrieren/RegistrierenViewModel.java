package com.example.edelhome.ui.Registrieren;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrierenViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegistrierenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}