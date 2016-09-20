package com.example.tyler.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.mock.MockContext;
import android.widget.Toast;

/**
 * Created by Tyler on 17-09-2016.
 */
public class DatabaseLink extends SQLiteOpenHelper {

    public static String dbname="ExpenseManager", tablename="IncomeExpense";
    public static String colDate="Date", colCategory="Category", colAmount="Amount", colDescription="Description";
    String sql, where, condition;
    boolean flag=false;

    public DatabaseLink(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + tablename + "(dtdate text, category text, type text, amount real, description text)");
        //sqLiteDatabase.execSQL("insert into " + tablename + " values('10/10/2016','Food',12.35,'fruits')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInfo(SQLiteDatabase  sq, String date, String category, String type, String amount, String description)
    {
        ContentValues cv = new ContentValues();
        cv.put("dtdate",date);
        cv.put("category",category);
        cv.put("type",type);
        cv.put("amount",amount);
        cv.put("description",description);

        sq.insert("IncomeExpense",null,cv);
        //Toast.makeText(new MockContext(), "Data added",Toast.LENGTH_LONG);
    }

    public Cursor getInfo(SQLiteDatabase  sq, String fromDate, String toDate, String type)
    {
        Cursor c;

        sql = "select * from IncomeExpense ";
        where = " where ";
        condition="";

        if(!fromDate.isEmpty() && !toDate.isEmpty()) {
            condition = condition + "dtdate between '" + fromDate + "' and '" + toDate + "' ";
            flag = true;
        }
        if(!type.isEmpty()) {
            condition = condition + "and type='" + type + "' ";
            flag = true;
        }

        if(flag)
            c = sq.rawQuery(sql+where+condition,null);
        else
            c = sq.rawQuery(sql,null);
        return c;
    }
}