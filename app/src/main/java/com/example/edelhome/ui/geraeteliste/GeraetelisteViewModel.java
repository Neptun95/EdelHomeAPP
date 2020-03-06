package com.example.edelhome.ui.geraeteliste;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GeraetelisteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GeraetelisteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Deine Geräte");
    }

    public LiveData<String> getText() {
        return mText;
    }
}