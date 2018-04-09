package net.homenet.a06_02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Region;
import android.view.View;

public class CustomViewStyles extends View {

    private Paint rectPaint = new Paint();
    private Paint circlePaint = new Paint();
    private Paint textPaint = new Paint();
    private DashPathEffect effect = new DashPathEffect(new float[]{5, 5}, 1);

    public CustomViewStyles(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setColor(Color.RED);
        canvas.drawRect(10, 10, 100, 100, rectPaint);

        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(2.0F);
        rectPaint.setColor(Color.GREEN);
        canvas.drawRect(10, 10, 100, 100, rectPaint);

        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setARGB(128, 0, 0, 255);
        canvas.drawRect(120, 10, 210, 100, rectPaint);

        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(3.0F);
        rectPaint.setPathEffect(effect);
        rectPaint.setColor(Color.GREEN);
        canvas.drawRect(120, 10, 210, 100, rectPaint);

        circlePaint.setColor(Color.MAGENTA);
        canvas.drawCircle(50, 160, 40, circlePaint);

        circlePaint.setAntiAlias(true);
        canvas.drawCircle(160, 160, 40, circlePaint);

        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1.0F);
        textPaint.setColor(Color.MAGENTA);
        textPaint.setTextSize(30.0F);
        canvas.drawText("Text (Stroke)", 20, 260, textPaint);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(30.0F);
        canvas.drawText("Text", 20, 300, textPaint);

        canvas.clipRect(220, 240, 250, 270, Region.Op.REPLACE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        canvas.drawRect(220, 240, 320, 340, textPaint);
    }
}
