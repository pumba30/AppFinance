package com.pundroid.appfinance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by pumba30 on 10.08.2015.
 */
public class Content extends AppCompatActivity {
    private long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        Intent intent = getIntent();
        id = intent.getLongExtra(MainActivity.ACTIVITY, -1);

        TextView tvIdActivity = (TextView) findViewById(R.id.id_activity);
        tvIdActivity.setText(String.valueOf(id));
        String titleActivity = getResources().getString(R.string.title_activity);

        getSupportActionBar().setTitle(titleActivity);


    }
}
