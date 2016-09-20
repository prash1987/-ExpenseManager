package com.example.tyler.expensemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tyler on 16-09-2016.
 */
public class CalculateTax extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calculatetax);

        Button btnCalculateTax = (Button) findViewById(R.id.btnCalculate);
        final TextView tvTax = (TextView) findViewById(R.id.tvTax);
        final EditText etIncome = (EditText) findViewById(R.id.etIncome);
        final Spinner spnrState = (Spinner) findViewById(R.id.spnrState);
        final Spinner spnrTaxRate = (Spinner) findViewById(R.id.spnrTaxRate);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.States, android.R.layout.simple_spinner_item);
        spnrState.setAdapter(adapter);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.TaxRate, android.R.layout.simple_spinner_item);
        spnrTaxRate.setAdapter(adapter2);

        btnCalculateTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etIncome.getText().toString().isEmpty())
                    Toast.makeText(getBaseContext(),"Enter Income",Toast.LENGTH_LONG).show();
                else
                {
                    float taxrate = Float.parseFloat(spnrTaxRate.getItemAtPosition(spnrState.getSelectedItemPosition()).toString());
                    float tax = Float.parseFloat(etIncome.getText().toString()) * taxrate /100;
                    tvTax.setText("Tax is " + tax);
                }
            }
        });

    }

}
