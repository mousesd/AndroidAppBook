package net.homenet.chanllenge07;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Button birthdayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEditText = this.findViewById(R.id.nameEditText);
        final EditText ageEditText = this.findViewById(R.id.ageEditText);

        birthdayButton = this.findViewById(R.id.birthdayButton);
        birthdayButton.setText(dateFormat.format(new Date()));
        birthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String birthdayString = birthdayButton.getText().toString();
                Date birthDay = null;
                try {
                    birthDay = dateFormat.parse(birthdayString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(birthDay);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, birthdaySetListener, year, month, day);
                dialog.show();
            }
        });

        Button saveButton = this.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, String.format("Name: %s", nameEditText.getText()));
                Log.d(TAG, String.format("Age: %s", ageEditText.getText()));
                Log.d(TAG, String.format("Birthday: %s", birthdayButton.getText()));
            }
        });
    }

    private DatePickerDialog.OnDateSetListener birthdaySetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            Date birthday = calendar.getTime();
            birthdayButton.setText(dateFormat.format(birthday));
        }
    };
}
