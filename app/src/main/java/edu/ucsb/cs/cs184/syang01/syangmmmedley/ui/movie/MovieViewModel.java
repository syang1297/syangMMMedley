package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MovieViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is movie fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}