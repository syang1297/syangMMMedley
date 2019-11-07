package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.multitouch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.ucsb.cs.cs184.syang01.syangmmmedley.R;

public class MultitouchFragment extends Fragment {

    private MultitouchViewModel multitouchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        multitouchViewModel =
                ViewModelProviders.of(this).get(MultitouchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fireworks, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        multitouchViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}