package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.texttospeech;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import edu.ucsb.cs.cs184.syang01.syangmmmedley.R;

public class TextToSpeechFragment extends Fragment implements View.OnClickListener {

    private TextToSpeechViewModel textToSpeechViewModel;
    private boolean finishInit = false;
    TextToSpeech tts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        textToSpeechViewModel =
                ViewModelProviders.of(this).get(TextToSpeechViewModel.class);
        View root = inflater.inflate(R.layout.fragment_texttospeech, container, false);
        Context context = getContext();
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                finishInit = true;
            }
        });
        tts.setLanguage(Locale.US);
        FloatingActionButton fab = root.findViewById(R.id.fabSpeaker);
        fab.setOnClickListener(this);
        return root;
    }

    public void onClick(View v){
        if(v.getId() == R.id.fabSpeaker){
            if(finishInit){
                EditText edit = getView().findViewById(R.id.editText);
                String text = edit.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                    return;
                } else {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    return;
                }
            }
        }
    }
}