package net.homenet.challenge13;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Animation translateIn;
    private Animation translateOut;
    private CustomerView customerView1;
    private CustomerView customerView2;

    private Handler handler = new Handler();
    private boolean isRunning;
    private int selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout container = this.findViewById(R.id.container);

        customerView1 = new CustomerView(this);
        customerView1.setName("김준수");
        customerView1.setAddress("서울시");
        customerView1.setMobile("000-1111-1111");
        container.addView(customerView1);

        customerView2 = new CustomerView(this);
        customerView2.setName("이희선");
        customerView2.setAddress("경기도");
        customerView2.setMobile("999-8888-7777");
        container.addView(customerView2);

        translateIn = AnimationUtils.loadAnimation(this, R.anim.translate_in);
        translateOut = AnimationUtils.loadAnimation(this, R.anim.translate_out);

        Button startButton = this.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationThread thread = new AnimationThread();
                thread.start();
            }
        });

        Button stopButton = this.findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });
    }

    private class AnimationThread extends Thread {
        @Override
        public void run() {
            isRunning = true;
            while (isRunning) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (selected % 2 == 0) {
                            customerView1.startAnimation(translateOut);
                            customerView2.startAnimation(translateIn);
                        } else {
                            customerView1.startAnimation(translateIn);
                            customerView2.startAnimation(translateOut);
                        }
                    }
                });

                selected++;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        }
    }
}
