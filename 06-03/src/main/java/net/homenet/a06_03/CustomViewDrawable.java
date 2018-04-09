package net.homenet.a06_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

@SuppressWarnings("PointlessArithmeticExpression")
public class CustomViewDrawable extends View {

    private ShapeDrawable upperDrawable;
    private ShapeDrawable lowerDrawable;
    private Paint paint;
    private Path path;

    public CustomViewDrawable(Context context) {
        super(context);

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (manager == null)
            return;

        Display display = manager.getDefaultDisplay();
        Point sizePoint = new Point();
        display.getSize(sizePoint);
        int width = sizePoint.x;
        int height = sizePoint.y;

        int blackColor = this.getResources().getColor(R.color.color01);
        int grayColor = this.getResources().getColor(R.color.color02);
        int darkGrayColor = this.getResources().getColor(R.color.color03);

        upperDrawable = new ShapeDrawable();
        RectShape rectShape1 = new RectShape();
        rectShape1.resize(width, height * 2 / 3);
        upperDrawable.setShape(rectShape1);
        upperDrawable.setBounds(0, 0, width, height * 2 / 3);

        LinearGradient gradient1 = new LinearGradient(0, 0, 0, height * 2 / 3, grayColor, blackColor, Shader.TileMode.CLAMP);
        upperDrawable.getPaint().setShader(gradient1);

        lowerDrawable = new ShapeDrawable();
        RectShape rectShape2 = new RectShape();
        rectShape2.resize(width, height * 1 / 3);
        lowerDrawable.setShape(rectShape2);
        lowerDrawable.setBounds(0, height * 2 / 3, width, height);

        LinearGradient gradient2 = new LinearGradient(0, 0, 0, height * 1 / 3, blackColor, darkGrayColor, Shader.TileMode.CLAMP);
        lowerDrawable.getPaint().setShader(gradient2);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16.0f);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        upperDrawable.draw(canvas);
        lowerDrawable.draw(canvas);

        paint.setColor(Color.YELLOW);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeJoin(Paint.Join.MITER);

        path.moveTo(20, 20);
        path.lineTo(120, 20);
        path.lineTo(160, 90);
        path.lineTo(180, 80);
        path.lineTo(200, 120);
        canvas.drawPath(path, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        path.offset(30, 120);
        canvas.drawPath(path, paint);

        paint.setColor(Color.CYAN);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        path.offset(30, 120);
        canvas.drawPath(path, paint);
    }
}
