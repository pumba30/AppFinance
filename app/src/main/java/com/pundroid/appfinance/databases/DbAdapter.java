package com.pundroid.appfinance.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by pumba30 on 12.08.2015.
 */
public class DbAdapter {
    private static final String TAG = DbAdapter.class.getSimpleName();
    private static String DB_PATH = "";
    private static final String DB_NAME = "db_finance";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "operation";

    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public DbAdapter(Context context) {
        Log.d(TAG, "constructor DbAdapter");
        this.context = context;

    }

    public void createDatabase() throws IOException, SQLException {
        Log.d(TAG, "attempt create DB");
        dbHelper = new DbHelper(context);
        dbHelper.createDataBase();
    }

    public void openDatabase() throws SQLException {
        dbHelper.openDataBase();
    }

    public void getTransactionList() {
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //выведем на экран значение поля "amount"

            String s = String.valueOf(cursor.getString(cursor.getColumnIndex("amount")));

            Log.d(TAG, s);

        }

        dbHelper.close();
    }

    //////////////////////////////////////////////
    private class DbHelper extends SQLiteOpenHelper {

        //Takes and keeps a reference of the passed context in order to access to the application assets and resources.
        public DbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);

            if (android.os.Build.VERSION.SDK_INT >= 17) {
                DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            } else {
                DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            }
            //this.context = context;
            Log.d(TAG, "constructor DbHelper");
        }


         // Creates a empty database on the system and rewrites it with your own database.


        public void createDataBase() throws IOException, SQLException {
            //If database not exists copy it from the assets
            boolean dataBaseExist = checkDataBase();

            if (!dataBaseExist) {
                this.getReadableDatabase();
                this.close();
                try {
                    //Copy the database from assets
                    copyDataBase();
                    Log.d(TAG, "createDatabase database created");
                } catch (IOException ex) {
                    throw new Error("ErrorCopyingDataBase");
                }
            }

        }

        private void copyDataBase() throws IOException {

            //Open  local db as the input stream
            InputStream myInput = context.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }


         //    Check if the database already exist to avoid re-copying the file each time you open the application.
         //   @return true if it exists, false if it doesn't

        private boolean checkDataBase() {
            File dbFile = new File(DB_PATH + DB_NAME);
            Log.d(TAG, dbFile + "   " + dbFile.exists());
            return dbFile.exists();
        }

        public void openDataBase() throws SQLException {
            //Open the database
            String myPath = DB_PATH + DB_NAME;
            database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        }

        @Override
        public synchronized void close() {

            if (database != null)
                database.close();

            super.close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }

}
