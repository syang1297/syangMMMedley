package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.speechtotext;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

import edu.ucsb.cs.cs184.syang01.syangmmmedley.R;

public class SpeechToTextFragment extends Fragment implements View.OnClickListener {

    private SpeechToTextViewModel speechToTextViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        speechToTextViewModel =
                ViewModelProviders.of(this).get(SpeechToTextViewModel.class);
        View root = inflater.inflate(R.layout.fragment_speechtotext, container, false);
        FloatingActionButton fabb = root.findViewById(R.id.fabSpeech);
        fabb.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v){
        int REQUEST_CODE = 100;
        Intent intent	= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//	prompt	text	is	shown	on	screen to tell user	what to say
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,	"text");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        }	catch	(ActivityNotFoundException anfe) {
            TextView text = getView().findViewById(R.id.textView2);
            text.setText("Device does not have speech to text capability.");
        }
//        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        ArrayList<String> list = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        String	spokenText = list.get(0);
        TextView text = getView().findViewById(R.id.textView2);
        text.setText(spokenText);
    }
}