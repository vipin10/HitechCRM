package android.hitech.com.hitechcrm;

import android.app.Activity;
import android.content.Intent;
import android.hitech.com.hitechcrm.Navigation_package.Nav_drawer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onLogin(View view){
        startActivity(new Intent(Login.this,Nav_drawer.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
