package android.hitech.com.hitechcrm.Task;


import android.content.Intent;
import android.hitech.com.hitechcrm.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by vipin.rai on 8/23/2017.
 */

public class Task1 extends Fragment implements View.OnClickListener {
    boolean isFabOpen = false;
    FloatingActionButton fab;
    LinearLayout l1, l2;
    RelativeLayout rl;
    Button b1,b2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vv=inflater.inflate(R.layout.task1,container,false);

        return vv;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        b1=(Button)view.findViewById(R.id.btn1);
        b2=(Button)view.findViewById(R.id.btn2);
        l1=(LinearLayout)view.findViewById(R.id.lFab1);
        l2=(LinearLayout)view.findViewById(R.id.lFab2);
        fab.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Log.d("Ankush", "asdfghjk");
                animateFAB();
                break;
            case R.id.btn1:
                startActivity(new Intent(getActivity(), CreateTask.class));
                Log.d("Ankush", "Fab 1");
                break;
            case R.id.btn2:
                startActivity(new Intent(getActivity(), CreateTask.class));
                Log.d("Ankush", "Fab 2");
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            fab.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward));
            l1.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fab_close));
            l2.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fab_close));
            isFabOpen = false;

        } else {
            fab.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward));
            l1.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fab_open));
            l2.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fab_open));
            isFabOpen = true;
        }
    }
}
