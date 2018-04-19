package net.homenet.a06_05;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintBoard extends View {

    private final Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    private int lastX;
    private int lastY;

    public PaintBoard(Context context) {
        this(context, null);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.BLACK);

        lastX = -1;
        lastY = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null)
            canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currX = (int) event.getX();
        int currY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (lastX != -1) {
                    if (currX != lastX || currY != lastY)
                        canvas.drawLine(lastX, lastY, currX, currY, paint);
                }
                lastX = currX;
                lastY = currY;
                break;

            case MotionEvent.ACTION_MOVE:
                if (lastX != -1)
                    canvas.drawLine(lastX, lastY, currX, currY, paint);
                lastX = currX;
                lastY = currY;
                break;

            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;
                break;
        }

        this.invalidate();
        return true;
    }
}
