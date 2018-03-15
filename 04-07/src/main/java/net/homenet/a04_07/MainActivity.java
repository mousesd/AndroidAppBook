package net.homenet.a04_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = this.findViewById(R.id.textView);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.flow);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "onAnimationStart()");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationEnd()");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimationRepeat()");
            }
        });

        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.startAnimation(anim);
            }
        });
    }
}
