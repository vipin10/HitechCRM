package android.hitech.com.hitechcrm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends AppCompatActivity{
ProgressBar pb;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl=(RelativeLayout)findViewById(R.id.activity_splash);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
              isNetworkAvailable();
            }
        }, 3000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                isNetworkAvailable();
            }
        }, 3000);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null && activeNetworkInfo.isConnected()){
            pb.setVisibility(View.GONE);
            Intent i=new Intent(Splash.this,Login.class);
            startActivity(i);
        }else
        {
            Snackbar snackbar = Snackbar.make(rl, "No Active Internet Connection", Snackbar.LENGTH_INDEFINITE);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            snackbar.setAction("SETTINGS", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            });
            textView.setTextSize(15);
            textView.setTextColor(Color.RED);
            snackbar.show();
            pb.setVisibility(View.GONE);
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
