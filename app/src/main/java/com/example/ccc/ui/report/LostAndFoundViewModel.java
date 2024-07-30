package com.example.ccc.ui.report;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LostAndFoundViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LostAndFoundViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Lost And Found fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}