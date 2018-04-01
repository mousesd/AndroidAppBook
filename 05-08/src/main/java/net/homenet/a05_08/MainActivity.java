package net.homenet.a05_08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MonthAdapter adapter;
    private TextView monthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MonthAdapter(this);
        GridView monthView = this.findViewById(R.id.monthView);
        monthView.setAdapter(adapter);
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                MonthItem selectedItem = (MonthItem) adapter.getItem(position);
                int day = selectedItem.getDay();
                Log.d(TAG, String.format("Selected Day: %s", day));

                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();
            }
        });

        monthText = this.findViewById(R.id.monthText);
        this.setMonthText();

        Button monthPrevious = this.findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged();
                setMonthText();
            }
        });

        Button monthNext = this.findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setNextMonth();
                adapter.notifyDataSetChanged();
                setMonthText();
            }
        });
    }

    private void setMonthText() {
        int year = adapter.getYear();
        int month = adapter.getMonth();
        monthText.setText(String.format("%s-%s", year, month+1));
    }
}
