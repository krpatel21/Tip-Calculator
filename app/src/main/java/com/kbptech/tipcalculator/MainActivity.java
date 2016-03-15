package com.kbptech.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private String[] arraySpinner;
    private EditText subtotalEditText;
    private EditText tipEditText;
    private EditText taxRate;
    private EditText totalEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        subtotalEditText = (EditText) findViewById(R.id.subtotalEditText);
//        tipEditText = new EditText(this, R.id.tip);
        taxRate = (EditText) findViewById(R.id.taxRateEditText);
        totalEditText = (EditText) findViewById(R.id.totalEditText);

        subtotalEditText.setText("0.00");
        taxRate.setText("0.00");
        totalEditText.setText("0.00");


        this.arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };
        Spinner s = (Spinner) findViewById(R.id.tipspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        s.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
