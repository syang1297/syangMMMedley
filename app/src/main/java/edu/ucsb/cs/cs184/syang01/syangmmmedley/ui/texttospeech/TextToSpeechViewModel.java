package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.texttospeech;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextToSpeechViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TextToSpeechViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is texttospeech fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}