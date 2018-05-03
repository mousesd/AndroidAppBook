package net.homenet.a07_05;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView imageView;
    private ArrayList<Drawable> drawables = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = this.findViewById(R.id.imageView);

        Resources res = this.getResources();
        drawables.add(res.getDrawable(R.drawable.emo_im_laughing));
        drawables.add(res.getDrawable(R.drawable.emo_im_crying));
        drawables.add(res.getDrawable(R.drawable.emo_im_happy));
        drawables.add(res.getDrawable(R.drawable.emo_im_sad));
        drawables.add(res.getDrawable(R.drawable.emo_im_surprised));

        AnimationThread thread = new AnimationThread();
        thread.start();
    }

    private class AnimationThread extends Thread {
        @Override
        public void run() {
            for (int index = 0; index < 100; index++) {
                final Drawable drawable = drawables.get(index % 5);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        }
    }
}
