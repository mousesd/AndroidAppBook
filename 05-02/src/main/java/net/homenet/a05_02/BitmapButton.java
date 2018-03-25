package net.homenet.a05_02;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BitmapButton extends AppCompatButton {

    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_CLICKED = 1;

    public BitmapButton(Context context) {
        super(context);
        this.Initialize();
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.Initialize();
    }

    private void Initialize() {
        this.setBackgroundResource(R.drawable.bitmap_button_normal);
        this.setTextColor(Color.WHITE);
        this.setTextSize(this.getResources().getDimension(R.dimen.text_size));
        this.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.setBackgroundResource(R.drawable.bitmap_button_clicked);
                break;
            case MotionEvent.ACTION_UP:
                this.setBackgroundResource(R.drawable.bitmap_button_normal);
                break;
        }

        this.invalidate();
        return true;
    }
}
