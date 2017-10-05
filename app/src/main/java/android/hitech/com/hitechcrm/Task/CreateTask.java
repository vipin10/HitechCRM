package android.hitech.com.hitechcrm.Task;

import android.hitech.com.hitechcrm.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateTask extends AppCompatActivity {
    Spinner moduleSpinner;
    ArrayAdapter adap;
    String[] profileStr = {"Accounts", "Candidates", "Contacts", "Leads","Jobs","Company","Calls","Meetings","Mails","Agreement","Invoice","Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        moduleSpinner  = (Spinner) findViewById(R.id.spinnn);
        adap = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, profileStr);
        moduleSpinner.setAdapter(adap);
    }
}
