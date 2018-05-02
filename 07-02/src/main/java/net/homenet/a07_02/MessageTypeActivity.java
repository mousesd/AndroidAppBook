package net.homenet.a07_02;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MessageTypeActivity extends AppCompatActivity {

    private static final String TAG = MessageTypeActivity.class.getSimpleName();

    private TextView textView;
    private ProgressBar progressBar;

    private boolean isRunning = false;
    private ProgressHandler handler = new ProgressHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_type);

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
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
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

    @SuppressLint("HandlerLeak")
    private class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            progressBar.incrementProgressBy(5);
            if (progressBar.getProgress() == progressBar.getMax()) {
                textView.setText(R.string.done);
            } else {
                textView.setText(String.format(getString(R.string.working), progressBar.getProgress()));
            }
        }
    }
}
