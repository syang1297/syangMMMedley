package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.movie;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import edu.ucsb.cs.cs184.syang01.syangmmmedley.R;


public class MovieFragment extends Fragment {

    private MovieViewModel movieViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        movieViewModel =
                ViewModelProviders.of(this).get(MovieViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        videoPlayer(root);
        return root;
    }

    public void videoPlayer(View root) {
        VideoView videoHolder = root.findViewById(R.id.videoView);
        videoHolder.setMediaController(new MediaController(getActivity()));
        Uri video = Uri.parse("android.resource://edu.ucsb.cs.cs184.syang01.syangmmmedley/" +  R.raw.bigbuck);
        videoHolder.setVideoURI(video);
        videoHolder.start();
    }

}