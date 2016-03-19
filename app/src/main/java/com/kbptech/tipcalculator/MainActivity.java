package com.kbptech.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private String[] arraySpinner;
    private EditText subtotalEditText;
    private EditText tipEditText;
    private EditText taxRateEditText;
    private EditText totalEditText;
    private double billAmount=0;
    private double taxRate=0;
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        subtotalEditText = (EditText) findViewById(R.id.subtotalEditText);
//        tipEditText = new EditText(this, R.id.tip);
        taxRateEditText = (EditText) findViewById(R.id.taxRateEditText);
        totalEditText = (EditText) findViewById(R.id.totalEditText);

//        subtotalEditText.setText("0.00");
//        taxRateEditText.setText("0.0");
//        totalEditText.setText("0.00");

        subtotalEditText.addTextChangedListener(amountEditTextWatcher);
        taxRateEditText.addTextChangedListener(taxRateTextWatcher);
        arraySpinner = new String[30];

        for (int i = 0; i < 30; i++){
            arraySpinner[i] = Integer.toString(i);
        }

        s = (Spinner) findViewById(R.id.tipspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        s.setAdapter(adapter);
        s.setSelection(15);
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
    private TextWatcher amountEditTextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try
            {
                billAmount = Double.parseDouble(charSequence.toString());
            } // end try
            catch (NumberFormatException e)
            {
                billAmount = 0.0; // default if an exception occurs
            } // end catch
            update();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher taxRateTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        update();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void update() {
        if (!taxRateEditText.getText().toString().equals("")) {
            taxRate = Double.parseDouble(taxRateEditText.getText().toString());
        }
        else {
            taxRate = 0;
        }

        double tip = Double.parseDouble(s.getSelectedItem().toString());
        double total = billAmount + billAmount * (taxRate / 100.0) + billAmount* (tip / 100.0);
        totalEditText.setText(currencyFormat.format(total));
    }

}
