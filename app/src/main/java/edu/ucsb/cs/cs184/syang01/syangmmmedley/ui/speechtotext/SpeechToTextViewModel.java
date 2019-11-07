package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.speechtotext;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpeechToTextViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpeechToTextViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is speechtotext fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}