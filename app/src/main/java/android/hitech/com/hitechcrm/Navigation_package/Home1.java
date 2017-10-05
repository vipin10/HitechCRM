package android.hitech.com.hitechcrm.Navigation_package;


import android.hitech.com.hitechcrm.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vipin.rai on 8/23/2017.
 */

public class Home1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vv=View.inflate(getActivity(), R.layout.home1,container);
        return vv;
    }
}
