package net.homenet.a07_02;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PostTypeActivity extends AppCompatActivity {

    private static final String TAG = PostTypeActivity.class.getSimpleName();

    private TextView textView;
    private ProgressBar progressBar;

    private boolean isRunning = false;
    private Handler handler = new Handler();
    private ProgressRunnable runnable = new ProgressRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_type);

        textView = this.findViewById(R.id.textView);
        progressBar = this.findViewById(R.id.progressBar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        progressBar.setProgress(0);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int count = 0; count < 20 && isRunning; count++) {
                        Thread.sleep(1000);
                        handler.post(runnable);
                    }
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });

        isRunning = true;
        thread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        isRunning = false;
    }

    private class ProgressRunnable implements Runnable {
        @Override
        public void run() {
            progressBar.incrementProgressBy(5);
            if (progressBar.getProgress() == progressBar.getMax()) {
                textView.setText(R.string.done);
            } else {
                textView.setText(String.format(getString(R.string.working), progressBar.getProgress()));
            }
        }
    }
}
