package edu.ucsb.cs.cs184.syang01.syangmmmedley.ui.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.ucsb.cs.cs184.syang01.syangmmmedley.R;

public class MultitouchFragment extends Fragment implements View.OnTouchListener/*, View.OnClickListener*/{
    private MultitouchViewModel multitouchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        multitouchViewModel =
                ViewModelProviders.of(this).get(MultitouchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_multitouch, container, false);
        Context context = getContext();
        final MultitouchSurface surf = new MultitouchSurface(context);
        surf.setId(R.id.multitouch);
        surf.surfaceCreated(surf.surfaceHolder);
        surf.setOnTouchListener(this);
        return root;

    }

    @Override
    public boolean onTouch(View view, MotionEvent event){
        view.onTouchEvent(event);
        return true;
    }

//    @Override
//    public void onClick (View v){
////        v.clear();
//        surf.clear();
//    }
}
