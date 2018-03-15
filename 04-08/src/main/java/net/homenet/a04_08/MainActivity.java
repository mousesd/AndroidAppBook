package net.homenet.a04_08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private boolean isPageOpen = false;
    private LinearLayout slidingPage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingPage = this.findViewById(R.id.slidingPage);
        final Animation translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        final Animation translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeft.setAnimationListener(animationListener);
        translateRight.setAnimationListener(animationListener);

        button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPageOpen) {
                    slidingPage.setVisibility(View.VISIBLE);
                    slidingPage.startAnimation(translateLeft);
                } else {
                    slidingPage.startAnimation(translateRight);
                }
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (!isPageOpen) {
                isPageOpen = true;
                button.setText(R.string.close);
            } else {
                isPageOpen = false;
                button.setText(R.string.open);
                slidingPage.setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
