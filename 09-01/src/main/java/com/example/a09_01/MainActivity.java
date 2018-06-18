package com.example.a09_01;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SQLiteDatabase database;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = this.findViewById(R.id.statusTextView);
        final EditText databaseNameEditText = this.findViewById(R.id.databaseNameEditText);
        Button createDatabaseButton = this.findViewById(R.id.createDatabaseButton);
        createDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = databaseNameEditText.getText().toString();
                if (databaseName.isEmpty())
                    return;

                createDatabase(databaseName);
            }
        });

        final EditText tableNameEditText = this.findViewById(R.id.tableNameEditText);
        Button createTableButton = this.findViewById(R.id.createTableButton);
        createTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = tableNameEditText.getText().toString();
                if (tableName.isEmpty())
                    return;

                createTable(tableName);
                insertRecords(tableName);
            }
        });
    }

    private void createDatabase(String databaseName) {
        this.printInfo("Creating database [" + databaseName + "]");

        try {
            database = this.openOrCreateDatabase(databaseName
                    , Activity.MODE_PRIVATE
                    , null);
            this.printInfo("Database is created.");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.printInfo("Database is not created.");
        }
    }

    private void createTable(String tableName) {
        if (database == null)
            return;

        this.printInfo("Creating table [" + tableName + "]");
        database.execSQL("create table if not exists " + tableName + "(" +
                " _id integer PRIMARY KEY autoincrement, " +
                " name text, " +
                " age integer, " +
                " phone text);");
        this.printInfo("Table is created");
    }

    private void insertRecords(String tableName) {
        if (database == null)
            return;

        this.printInfo("Inserting records into table " + tableName);

        database.execSQL("insert into " + tableName + "(name, age, phone) values ('John', 20, '111-1111-1111')");
        database.execSQL("insert into " + tableName + "(name, age, phone) values ('Mike', 35, '222-2222-2222')");
        database.execSQL("insert into " + tableName + "(name, age, phone) values ('Sean', 26, '333-3333-3333')");
    }

    private void printInfo(String message) {
        Log.d(TAG, message);
        statusTextView.append("\n" + message);
    }
}
