package net.homenet.a07_08;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Animation dropAnim;
    private Animation flowAnim;
    private Animation shakeAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView skyImage = this.findViewById(R.id.skyImage);
        flowAnim = AnimationUtils.loadAnimation(this, R.anim.flow);
        skyImage.setAnimation(flowAnim);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.sky_background);
        ViewGroup.LayoutParams params = skyImage.getLayoutParams();
        params.width = bitmap.getWidth();
        params.height = bitmap.getHeight();
        skyImage.setImageBitmap(bitmap);

        flowAnim.setAnimationListener(new AnimationAdapter());

        ImageView swingImage = this.findViewById(R.id.swingImage);
        shakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake);
        swingImage.setAnimation(shakeAnim);

        ImageView waterImage = this.findViewById(R.id.waterImage);
        dropAnim = AnimationUtils.loadAnimation(this, R.anim.drop);
        waterImage.setAnimation(dropAnim);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            dropAnim.start();
            flowAnim.start();
            shakeAnim.start();
        } else {
            dropAnim.reset();
            flowAnim.reset();
            shakeAnim.reset();
        }
    }

    private class AnimationAdapter implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            Log.d(TAG, "Flow animation started.");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Log.d(TAG, "Flow animation stoped.");
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            Log.d(TAG, "Flow animation repeated.");
        }
    }
}
