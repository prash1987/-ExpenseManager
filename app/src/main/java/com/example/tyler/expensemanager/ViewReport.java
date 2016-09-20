package com.example.tyler.expensemanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tyler on 16-09-2016.
 */
public class ViewReport extends Activity {

    Button btnShowReport;
    EditText etFrom, etTo;
    RadioButton radIncome, radExpense;
    TextView tvResult;

    DatabaseLink db;
    SQLiteDatabase sq;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewreport);


        btnShowReport = (Button) findViewById(R.id.btnShowReport);

        etFrom = (EditText) findViewById(R.id.etFrom);
        etTo = (EditText) findViewById(R.id.etTo);
        radIncome = (RadioButton) findViewById(R.id.radIncome);
        radExpense = (RadioButton) findViewById(R.id.radExpense);
        tvResult = (TextView) findViewById(R.id.tvResult);

        db=new DatabaseLink(this);
        sq=db.getReadableDatabase();

        btnShowReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c;
                StringBuffer sb = new StringBuffer();

                DateFormat formatter ;
                Date frmdate=null, todate=null ;
                formatter = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    frmdate = (Date)formatter.parse(etFrom.getText().toString());
                    todate = (Date) formatter.parse(etTo.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if(radExpense.isChecked())
                    type="E";
                if(radIncome.isChecked())
                    type="I";

                //c = db.getInfo(sq,frmdate.toString(),todate.toString(),type);
                c = db.getInfo(sq,"01/01/2016","12/12/2016",type);

                if(c.getCount()==0)
                    Toast.makeText(getBaseContext(),"No records found",Toast.LENGTH_LONG).show();
                else {
                    c.moveToFirst();
                    do{
                        sb.append(""+ c.getString(0)+"    "+ c.getString(1)+ "   "+ c.getString(3)+"\n");
                    }while(c.moveToNext());
                    tvResult.setText(sb.toString());

                    Toast.makeText(getBaseContext(),"Number of records: " + c.getCount(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}