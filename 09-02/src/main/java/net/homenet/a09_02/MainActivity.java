package net.homenet.a09_02;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TABLE_NAME = "employee";

    private SQLiteDatabase database;
    private CustomerDatabase customerDb;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = this.findViewById(R.id.statusTextView);
        final EditText databaseNameEditText = this.findViewById(R.id.databaseNameEditText);
        Button createButton = this.findViewById(R.id.createDatabaseButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = databaseNameEditText.getText().toString();
                if (databaseName.isEmpty())
                    return;

                customerDb = new CustomerDatabase(MainActivity.this, databaseName);
                database = customerDb.getWritableDatabase();

                executeRawQuery();
                executeRawQueryParams();
            }
        });
    }

    private void executeRawQuery() {
        this.printInfo("\nexecuteRawQuery called");

        Cursor cursor = database.rawQuery("select count(*) from " + TABLE_NAME, null);
        this.printInfo("Cursor count: " + cursor.getCount());

        cursor.moveToNext();
        this.printInfo("Record count: " + cursor.getInt(0));
        cursor.close();
    }

    private void executeRawQueryParams() {
        this.printInfo("\nexecuteRawQueryParams called");

        String selectSql = "select name, age, phone" +
            " from " + TABLE_NAME +
            " where age > ?";
        String[] selectionArgs = {"30"};

        Cursor cursor = database.rawQuery(selectSql, selectionArgs);
        this.printInfo("Cursor count: " + cursor.getCount());

        for (int index = 0; index < cursor.getCount(); index++) {
            cursor.moveToNext();
            String name = cursor.getString(0);
            int age = cursor.getInt(1);
            String phone = cursor.getString(2);

            this.printInfo(String.format("Record #%s: %s, %s, %s", index, name, age, phone));
        }
        cursor.close();
    }

    private void printInfo(String message) {
        Log.d(TAG, message);
        statusTextView.append("\n" + message);
    }

    private class CustomerDatabase extends SQLiteOpenHelper {

        private static final int VERSION = 1;

        CustomerDatabase(Context context, String name) {
            super(context, name, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            printInfo("Creating table [" + TABLE_NAME + "]");

            String dropSql = "drop table if exists " + TABLE_NAME;
            try {
                database.execSQL(dropSql);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in drop table", ex);
            }

            String createSql = "create table " + TABLE_NAME + "(" +
                " _id integer primary key autoincrement," +
                " name text," +
                " age integer," +
                " phone text)";

            try {
                database.execSQL(createSql);
            } catch (Exception ex) {
                Log.e(TAG, "Exception in create table", ex);
            }

            printInfo("Inserting records");

            try {
                database.execSQL("insert into " + TABLE_NAME + " (name, age, phone) values ('John', 30, '111-1111-1111')");
                database.execSQL("insert into " + TABLE_NAME + " (name, age, phone) values ('Mike', 35, '222-2222-2222')");
                database.execSQL("insert into " + TABLE_NAME + " (name, age, phone) values ('Sean', 26, '333-3333-3333')");
            } catch (Exception ex) {
                Log.e(TAG, "Exception in insert record", ex);
            }
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            printInfo("Database is opened.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            printInfo("Upgrading database from verson " + oldVersion + " to " + newVersion);
        }
    }
}
