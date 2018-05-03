package net.homenet.a07_06;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageSwitcher imageSwitcher;
    private Handler handler = new Handler();
    private ImageSwitcherThread thread;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = this.findViewById(R.id.imageSwitcher);
        imageSwitcher.setVisibility(View.INVISIBLE);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setBackgroundColor(0xFF000000);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });

        Button executeButton = this.findViewById(R.id.executeButton);
        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageSwitcher.setVisibility(View.VISIBLE);
                thread = new ImageSwitcherThread();
                thread.start();
            }
        });

        Button stopButton = this.findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thread == null)
                    return;

                isRunning = false;
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }

                imageSwitcher.setVisibility(View.INVISIBLE);
            }
        });
    }

    private class ImageSwitcherThread extends Thread {

        private int currentIndex = 0;
        private final int imageId[] = {R.drawable.emo_im_crying, R.drawable.emo_im_happy, R.drawable.emo_im_laughing
            , R.drawable.emo_im_surprised};

        @Override
        public void run() {
            isRunning = true;
            while (isRunning) {
                synchronized (this) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageSwitcher.setImageResource(imageId[currentIndex]);
                        }
                    });

                    currentIndex++;
                    if (currentIndex == imageId.length)
                        currentIndex = 0;

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }
        }
    }
}
