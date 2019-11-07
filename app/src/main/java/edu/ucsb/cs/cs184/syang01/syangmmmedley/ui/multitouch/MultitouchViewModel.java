package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.multitouch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MultitouchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MultitouchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is multitouch fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}