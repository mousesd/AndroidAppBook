package net.homenet.a06_04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class CustomViewImage extends View {

    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;
    private Paint paint;

    public CustomViewImage(Context context) {
        super(context);

        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.createBitmap(w, h);
    }

    private void createBitmap(int width, int height) {
        cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        cacheCanvas.drawColor(Color.WHITE);
        cacheCanvas.drawRect(100, 100, 200, 200, paint);

        Bitmap waterDrop = BitmapFactory.decodeResource(this.getResources(), R.drawable.waterdrop);
        cacheCanvas.drawBitmap(waterDrop, 30, 30, paint);

        Matrix matrix1 = new Matrix();
        matrix1.setScale(-1, 1);
        Bitmap bitmap1 = Bitmap.createBitmap(waterDrop, 0, 0, waterDrop.getWidth()
            , waterDrop.getHeight(), matrix1, false);
        cacheCanvas.drawBitmap(bitmap1, 30, 130, paint);

        Matrix matrix2 = new Matrix();
        matrix2.setScale(1, -1);
        Bitmap bitmap2 = Bitmap.createBitmap(waterDrop, 0, 0, waterDrop.getWidth()
            , waterDrop.getHeight(), matrix2, false);
        cacheCanvas.drawBitmap(bitmap2, 30, 230, paint);

        Bitmap face = BitmapFactory.decodeResource(this.getResources(), R.drawable.face);
        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        Bitmap bitmap3 = Bitmap.createScaledBitmap(face, face.getWidth() * 2, face.getHeight() * 2, false);
        cacheCanvas.drawBitmap(bitmap3, 100, 230, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (cacheBitmap != null)
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
    }
}
