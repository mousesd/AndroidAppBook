package net.homenet.a06_05;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class PenPaletteDialog extends AppCompatActivity {

    public static OnPenSelectedListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_color_palette_dialog);

        this.setTitle(R.string.pen);

        GridView penGridView = this.findViewById(R.id.colorGridView);
        penGridView.setNumColumns(5);
        penGridView.setAdapter(new PenDataAdapter(this));

        Button closeButton = this.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public interface OnPenSelectedListener {
        void onPenSelected(int pen);
    }
}

class PenDataAdapter extends BaseAdapter {

    private Context context;
    private static final int[] pens = new int[] {
        1, 2, 3, 4, 5,
        6, 7, 8, 9, 10,
        11, 13, 15, 17, 20
    };

    PenDataAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pens.length;
    }

    @Override
    public Object getItem(int position) {
        return pens[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            GridView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);

            Button button = new Button(context);
            button.setText("");
            button.setLayoutParams(params);
            button.setPadding(4, 4, 4, 4);
            button.setHeight(120);
            button.setTag(pens[position]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PenPaletteDialog.listener != null)
                        PenPaletteDialog.listener.onPenSelected((Integer)v.getTag());
                    ((PenPaletteDialog) context).finish();
                }
            });

            convertView = button;
        }

        int width = 10;
        int height = 20;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, paint);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(pens[position]);
        canvas.drawLine(0, height / 2, width - 1, height / 2, paint);
        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        Button view = (Button) convertView;
        view.setBackground(drawable);

        return view;
    }
}