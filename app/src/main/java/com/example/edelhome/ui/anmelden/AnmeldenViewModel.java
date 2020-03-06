package com.example.edelhome.ui.anmelden;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnmeldenViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mMail;

    public AnmeldenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is  Anmelden digga fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setMail(String mail){
        mMail = new MutableLiveData<String>();
        mMail.setValue(mail);}

    public LiveData<String> getMail(){
        if(mMail.equals(null)){
            mMail.setValue("");
        }
        return mMail;
    }



}
