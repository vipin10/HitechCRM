package android.hitech.com.hitechcrm.Task;

import android.app.Activity;
import android.hitech.com.hitechcrm.R;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebView;


/**
 * Created by vipin.rai on 9/5/2017.
 */

public class Locca extends Activity {
WebView wvv;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.loccc);
        wvv=(WebView)findViewById(R.id.weeee);
        wvv.loadUrl("http://www.sahajmarg.org/homepage");
    }
}
