package net.homenet.a04_04;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText xEditText = this.findViewById(R.id.xEditText);
        final EditText yEditText = this.findViewById(R.id.yEditText);
        Button showButton = this.findViewById(R.id.showButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xOffset = Integer.parseInt(xEditText.getText().toString());
                int yOffset = Integer.parseInt(yEditText.getText().toString());

                Toast toast = Toast.makeText(getApplicationContext(), "Toast message", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, xOffset, yOffset);
                toast.show();
            }
        });

        Button changeButton = this.findViewById(R.id.changeButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text = layout.findViewById(R.id.text);
                text.setText(R.string.message);

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, -100);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });

        Button snackbarButton = this.findViewById(R.id.snackbarButton);
        snackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "This is the Snackbar", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
