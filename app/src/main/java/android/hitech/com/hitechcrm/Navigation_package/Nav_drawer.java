package android.hitech.com.hitechcrm.Navigation_package;


import android.content.Intent;
import android.hitech.com.hitechcrm.AddLead;
import android.hitech.com.hitechcrm.R;
import android.hitech.com.hitechcrm.Task.CreateTask;
import android.hitech.com.hitechcrm.Task.Task1;
import android.hitech.com.hitechcrm.ViewLead;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Nav_drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    boolean isFabOpen = false;
    FloatingActionButton fab;
    LinearLayout l1, l2;
    RelativeLayout rl;
    FragmentTransaction ft;
    Fragment ff;
    int itemid;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* fab = (FloatingActionButton) findViewById(R.id.fab);
        l1 = (LinearLayout) findViewById(R.id.lFab1);
        l2 = (LinearLayout) findViewById(R.id.lFab2);
        fab.setOnClickListener(this);*/
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_nav_drawer, new SampleFragment()).commit();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

  /*  @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                animateFAB();
                break;
            case R.id.btn1:
                startActivity(new Intent(Nav_drawer.this, CreateTask.class));
                Log.d("Ankush", "Fab 1");
                break;
            case R.id.btn2:
                startActivity(new Intent(Nav_drawer.this, CreateTask.class));
                Log.d("Ankush", "Fab 2");
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            fab.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_backward));
            l1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fab_close));
            l2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fab_close));
            isFabOpen = false;

        } else {
            fab.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_forward));
            l1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fab_open));
            l2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fab_open));
            isFabOpen = true;
        }
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        ft = getSupportFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this,"Home pressed",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_lead) {
            Intent i=new Intent(Nav_drawer.this, ViewLead.class);
            startActivity(i);
        } else if (id == R.id.nav_task) {
            ft.replace(R.id.content_nav_drawer,new Task1());
            ft.commit();

        } else if (id == R.id.nav_company) {
            Toast.makeText(this,"Tools pressed",Toast.LENGTH_LONG).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
