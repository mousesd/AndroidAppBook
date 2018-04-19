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

import java.util.Stack;

public class BestPaintBoard extends View {

    private static final int MAX_UNDO_SIZE = 10;
    private final Stack<Bitmap> undos = new Stack<>();
    private final Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;

    private int lastX;
    private int lastY;

    public BestPaintBoard(Context context) {
        this(context, null);
    }

    public BestPaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(2);
        paint.setDither(true);

        lastX = -1;
        lastY = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(Color.WHITE);
        this.invalidate();
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
                this.saveUndo();

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

    private void saveUndo() {
        if (bitmap == null)
            return;

        while (undos.size() >= MAX_UNDO_SIZE) {
            Bitmap firstBitmap = undos.get(0);
            firstBitmap.recycle();
            undos.remove(firstBitmap);
        }

        Bitmap undoBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()
            , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(undoBitmap);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        undos.push(undoBitmap);
    }

    public void updatePaintProperty(int color, int pen) {
        paint.setColor(color);
        paint.setStrokeWidth(pen);
    }

    public void undo() {
        if (undos.isEmpty())
            return;

        Bitmap prev = undos.pop();
        if (prev != null) {
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(prev, 0, 0, paint);
            this.invalidate();
            prev.recycle();
        }
    }
}
