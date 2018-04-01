package net.homenet.a05_09;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ImageDisplayView extends View implements View.OnTouchListener {

    private static final String TAG = ImageDisplayView.class.getSimpleName();
    private static final float DISTANCE_THRESHOLD = 3.0f;
    private static final float MIN_SCALE_RATIO = 0.1F;
    private static final float MAX_SCALE_RATIO = 5.0F;

    private Paint paint;
    private Matrix matrix;

    private Bitmap bitmap;
    private Canvas canvas;
    private Bitmap sourceBitmap;

    private float displayWidth;
    private float displayHeight;

    private float startX;
    private float startY;
    private float oldDistance;
    private boolean isScrolling;
    private int oldPointerCount;
    private float totalScaleRatio;
    private float scaleRatio;
    private float bitmapCenterX;
    private float bitmapCenterY;

    public ImageDisplayView(Context context) {
        super(context);
        this.initialize();
    }

    public ImageDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    private void initialize() {
        paint = new Paint();
        matrix = new Matrix();

        this.setOnTouchListener(this);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
            this.newImage(w, h);
            this.redraw();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null)
            canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private void newImage(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);

        this.bitmap = bitmap;
        this.canvas = canvas;

        displayWidth = (float) width;
        displayHeight = (float) height;
    }

    private void redraw() {
        if (this.sourceBitmap == null)
            return;

        this.drawBackground();

        float originX = (displayWidth - (float) sourceBitmap.getWidth()) / 2.0f;
        float originY = (displayHeight - (float) sourceBitmap.getHeight()) / 2.0f;

        canvas.translate(originX, originY);
        canvas.drawBitmap(sourceBitmap, matrix, paint);
        canvas.translate(-originX, -originY);
        this.invalidate();
    }

    private void drawBackground() {
        if (canvas != null)
            canvas.drawColor(Color.BLACK);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int pointerCount = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, String.format("ACTION_DOWN, Pointer Count: %s", pointerCount));
                if (pointerCount == 1) {
                    float currX = event.getX();
                    float currY = event.getY();

                    startX = currX;
                    startY = currY;
                } else if (pointerCount == 2) {
                    this.oldDistance = 0.0f;
                    this.isScrolling = true;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, String.format("ACTION_MOVE, Pointer Count: %s", pointerCount));
                if (pointerCount == 1) {
                    if (isScrolling)
                        return true;

                    float currX = event.getX();
                    float currY = event.getY();
                    if (startX == 0.0f) {
                        startX = currX;
                        startY = currY;
                        return true;
                    }

                    if (this.oldPointerCount != 2) {
                        float offsetX = startX - currX;
                        float offsetY = startY - currY;
                        Log.d(TAG, String.format("ACION_MOVE: (%s, %s)", offsetX, offsetY));

                        if (this.totalScaleRatio > 1.0f)
                            this.moveImage(-offsetX, -offsetY);

                        startX = currX;
                        startY = currY;
                    }
                } else if (pointerCount == 2) {
                    float x1 = event.getX(0);
                    float y1 = event.getY(0);
                    float x2 = event.getX(1);
                    float y2 = event.getY(1);

                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    float distance = Double.valueOf(Math.sqrt(Float.valueOf(dx * dx + dy * dy).doubleValue())).floatValue();
                    float outScaleRatio = 0.0f;
                    if (oldDistance == 0.0f) {
                        oldDistance = distance;
                        return true;
                    }

                    if (distance > oldDistance) {
                        if (distance - oldDistance < DISTANCE_THRESHOLD)
                            return true;
                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05f);
                    } else if (distance < oldDistance) {
                        if (oldDistance - distance < DISTANCE_THRESHOLD)
                            return true;
                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05f);
                    }

                    if (!(outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO)) {
                        Log.d(TAG, String.format("Distance: %s, ScaleRation: %s", distance, outScaleRatio));
                        this.scaleImage(outScaleRatio);
                    }
                    oldDistance = distance;
                }

                this.oldPointerCount = pointerCount;
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, String.format("ACTION_UP, Pointer Count: %s", pointerCount));
                if (pointerCount == 1) {
                    float currX = event.getX();
                    float currY = event.getY();
                    float offsetX = startX - currX;
                    float offsetY = startY - currY;

                    if (oldPointerCount != 2)
                        this.moveImage(-offsetX, -offsetY);
                } else {
                    isScrolling = true;
                }
                return true;
        }
        return true;
    }

    private void moveImage(float offsetX, float offsetY) {
        Log.d(TAG, String.format("moveImage(%s, %s)", offsetX, offsetY));
        matrix.postTranslate(offsetX, offsetY);
        this.redraw();
    }

    private void scaleImage(float ratio) {
        Log.d(TAG, String.format("scaleImage(%S)", ratio));
        matrix.postScale(ratio, ratio, bitmapCenterX, bitmapCenterY);
        matrix.postRotate(0.0f);

        totalScaleRatio = totalScaleRatio * ratio;
        this.redraw();
    }

    @SuppressWarnings("unused")
    public void setImageData(Bitmap bitmap) {
        this.recycle();

        sourceBitmap = bitmap;
        bitmapCenterX = sourceBitmap.getWidth() / 2;
        bitmapCenterY = sourceBitmap.getHeight() / 2;
        scaleRatio = 1.0f;
        totalScaleRatio = 1.0f;
    }

    private void recycle() {
        if (sourceBitmap != null)
            sourceBitmap.recycle();
    }
}
