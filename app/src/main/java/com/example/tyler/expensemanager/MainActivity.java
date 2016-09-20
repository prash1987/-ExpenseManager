package com.example.tyler.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddExpense = (Button) findViewById(R.id.button);
        Button btnAddIncome = (Button) findViewById(R.id.button2);
        Button btnViewReport = (Button) findViewById(R.id.button3);
        Button btnCalcTax = (Button) findViewById(R.id.button4);

        btnAddExpense.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0)
            {
                startActivity(new Intent("android.intent.action.AddExpense"));
            }

        });

        btnAddIncome.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0)
            {
                startActivity(new Intent("android.intent.action.AddIncome"));
            }

        });

        btnViewReport.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0)
            {
                startActivity(new Intent("android.intent.action.ViewReport"));
            }

        });

        btnCalcTax.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0)
            {
                startActivity(new Intent("android.intent.action.CalculateTax"));
            }

        });

    }
}
