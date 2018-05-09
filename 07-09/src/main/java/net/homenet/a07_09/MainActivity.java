package net.homenet.a07_09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Animation anim;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anim = AnimationUtils.loadAnimation(this, R.anim.grow);
        mainLayout = this.findViewById(R.id.mainLayout);

        this.addGraph("Apple", 80);
        this.addGraph("Orange", 100);
        this.addGraph("Kiwi", 40);
    }

    private void addGraph(String name, int value) {
        LinearLayout graphLayout = new LinearLayout(this);
        graphLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(this);
        textView.setText(name);
        params1.width = 240;
        params1.setMargins(0, 4, 0, 4);
        graphLayout.addView(textView, params1);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ProgressBar bar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        bar.setIndeterminate(false);
        bar.setMax(100);
        bar.setProgress(100);
        bar.setAnimation(anim);
        params2.height = 80;
        params2.width = value * 5;
        params2.gravity = Gravity.LEFT;
        graphLayout.addView(bar, params2);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.addView(graphLayout, params3);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus)
            anim.start();
        else
            anim.reset();
    }
}
