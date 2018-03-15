package net.homenet.a04_06;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = this.findViewById(R.id.progressBar);
        progressBar.setProgress(80);

        Button showButton = this.findViewById(R.id.showButton);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Inquiry data");
                dialog.show();
            }
        });

        Button dismissButton = this.findViewById(R.id.dismissButton);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        Button seekButton = this.findViewById(R.id.seekButton);
        seekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout seekBarLayout = findViewById(R.id.seekBarLayout);
                seekBarLayout.setVisibility(View.VISIBLE);
            }
        });

        final TextView seekTextView = this.findViewById(R.id.seekTextView);
        SeekBar seekBar = this.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int seek = i;
                if (seek < 10)
                    seek = 10;
                else if (seek > 100)
                    seek = 100;

                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.screenBrightness = (float)seek / 100;
                getWindow().setAttributes(params);

                seekTextView.setText(String.format("SeekBar Progress: %d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
