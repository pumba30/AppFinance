package com.pundroid.appfinance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.pundroid.appfinance.databases.DbAdapter;
import com.pundroid.appfinance.gui.MenuExpandableList;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static MenuExpandableList menuExpandableList;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (menuExpandableList == null) {
            menuExpandableList = new MenuExpandableList(this);
        }

         dbAdapter = new DbAdapter(this);
        try {
            Log.d(TAG, "create database");
            dbAdapter.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            dbAdapter.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbAdapter.getTransactionList();

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

