package net.homenet.a05_08;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MonthItemView extends AppCompatTextView {

    private MonthItem item;

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        this.setText(item.getDay() == 0 ? "" : String.valueOf(item.getDay()));
    }

    public MonthItemView(Context context) {
        super(context);
        this.initialize();
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    private void initialize() {
        this.setBackgroundColor(Color.WHITE);
    }
}
