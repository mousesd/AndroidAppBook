package net.homenet.a07_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private int value = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = this.findViewById(R.id.textView);
        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.format(getString(R.string.value_string), value));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        isRunning = true;
        Thread thread = new BackgroundThread();
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        isRunning = false;
    }

    private class BackgroundThread extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(1000);
                    value++;
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
