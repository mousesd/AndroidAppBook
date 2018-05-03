package net.homenet.a07_04;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;
    private BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = this.findViewById(R.id.textView);
        progressBar = this.findViewById(R.id.progressBar);

        Button executeButton = this.findViewById(R.id.executeButton);
        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new BackgroundTask();
                task.execute(100);
            }
        });

        Button cancelButton = this.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task != null)
                    task.cancel(true);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {

        private final String TAG = BackgroundTask.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            progressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int value = 0;
            int maxValue = integers[0];
            while (!this.isCancelled()) {
                value++;
                if (value >= maxValue) {
                    break;
                } else {
                    this.publishProgress(value);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }
            }

            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            textView.setText(String.format(getString(R.string.current_value), values[0]));
        }

        @Override
        protected void onPostExecute(Integer integer) {
            textView.setText(R.string.finished);
        }

        @Override
        protected void onCancelled(Integer integer) {
            textView.setText(R.string.cancelled);
        }
    }
}
