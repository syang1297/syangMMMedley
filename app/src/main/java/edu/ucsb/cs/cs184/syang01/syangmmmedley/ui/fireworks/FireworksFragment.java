package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.fireworks;

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

public class FireworksFragment extends Fragment {

    private FireworksViewModel fireworksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fireworksViewModel =
                ViewModelProviders.of(this).get(FireworksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fireworks, container, false);

        return root;
    }
}