package com.example.edelhome.ui.home;

import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edelhome.R;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ImageView imageView;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");



    }

    public LiveData<String> getText() {
        return mText;
    }
}