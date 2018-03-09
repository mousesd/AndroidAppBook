package net.homenet.a04_01;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = this.findViewById(R.id.textView);

        View view1 = this.findViewById(R.id.view1);
        view1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float currX = event.getX();
                float currY = event.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    printTextView(String.format("ACTION_DOWN: (%s,%s)", currX, currY));
                } else if (action == MotionEvent.ACTION_MOVE) {
                    printTextView(String.format("ACTION_MOVE: (%s,%s)", currX, currY));
                } else if (action == MotionEvent.ACTION_UP) {
                    printTextView(String.format("ACTION_UP: (%s,%s)", currX, currY));
                }

                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                printTextView("Gesture.onDown()");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                printTextView("Gesture.onShowPress()");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                printTextView("Gesture.onSingleTapUp()");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                printTextView(String.format("Gesture.onScroll(%s, %s)", distanceX, distanceY));
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                printTextView("Gesture.onLongPress()");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                printTextView(String.format("Gesture.onFling(%s, %s)", velocityX, velocityY));
                return true;
            }
        });

        View view2 = this.findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void printTextView(String text) {
        textView.append(text + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "Back button pressed", Toast.LENGTH_SHORT).show();
        }

        return super.onKeyDown(keyCode, event);
    }
}
