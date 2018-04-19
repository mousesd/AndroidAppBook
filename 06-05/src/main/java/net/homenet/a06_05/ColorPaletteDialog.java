package net.homenet.a06_05;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class ColorPaletteDialog extends AppCompatActivity {

    public static OnColorSelectedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_palette_dialog);

        this.setTitle(R.string.color);

        GridView colorGridView = this.findViewById(R.id.colorGridView);
        colorGridView.setNumColumns(7);
        colorGridView.setAdapter(new ColorAdapter(this));

        Button closeButton = this.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }
}

class ColorAdapter extends BaseAdapter {

    private Context context;
    private static final int[] colors = new int[] {
        0xff000000, 0xff00007f, 0xff0000ff, 0xff007f00, 0xff007f7f, 0xff00ff00, 0xff00ff7f,
        0xff00ffff, 0xff7f007f, 0xff7f00ff, 0xff7f7f00, 0xff7f7f7f, 0xffff0000, 0xffff007f,
        0xffff00ff, 0xffff7f00, 0xffff7f7f, 0xffff7fff, 0xffffff00, 0xffffff7f, 0xffffffff
    };

    ColorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ColorPaletteDialog.listener != null)
                        ColorPaletteDialog.listener.onColorSelected((Integer) v.getTag());

                    ((ColorPaletteDialog) context).finish();
                }
            });

            convertView = button;
        }

        Button view = (Button)convertView;
        view.setBackgroundColor(colors[position]);
        view.setTag(colors[position]);
        return view;
    }
}
