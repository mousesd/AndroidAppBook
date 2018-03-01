package net.homenet.a03_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = this.findViewById(R.id.button);
        final LinearLayout container = this.findViewById(R.id.container);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                if (inflater == null)
                    return;

                inflater.inflate(R.layout.layout_part, container, true);

                CheckBox checkBox = container.findViewById(R.id.checkBox);
                checkBox.setText(R.string.load_completed);
            }
        });
    }
}
