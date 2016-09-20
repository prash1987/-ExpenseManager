package com.example.tyler.expensemanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Tyler on 16-09-2016.
 */
public class AddExpense extends Activity {

    Button btnAdd;
    EditText etDate, etAmount, etDescription;
    Spinner spnrCategory;
    DatabaseLink db;
    SQLiteDatabase sq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addexpense);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        etDate = (EditText) findViewById(R.id.etDate);
        etAmount = (EditText) findViewById(R.id.etAmount);
        etDescription = (EditText) findViewById(R.id.etDescription);
        spnrCategory = (Spinner) findViewById(R.id.spnrCategory);
        db= new DatabaseLink(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.ExpenseCategory, android.R.layout.simple_spinner_item);
        spnrCategory.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0)
            {
                sq = db.getWritableDatabase();
                db.addInfo(sq, etDate.getText().toString(),spnrCategory.getSelectedItem().toString()," E",etAmount.getText().toString(),etDescription.getText().toString());
                Toast.makeText(getBaseContext(),"Info added",Toast.LENGTH_LONG).show();
            }
        });

    }
}
