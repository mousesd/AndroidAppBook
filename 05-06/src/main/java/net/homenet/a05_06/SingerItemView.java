package net.homenet.a05_06;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    private TextView nameTextView;
    private TextView mobileTextView;
    private TextView ageTextView;
    private ImageView imageView;

    public SingerItemView(Context context) {
        super(context);
        this.initialize(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null)
            return;

        inflater.inflate(R.layout.singer_item, this, true);
        nameTextView = this.findViewById(R.id.nameTextView);
        mobileTextView = this.findViewById(R.id.mobileTextView);
        ageTextView = this.findViewById(R.id.ageTextView);
        imageView = this.findViewById(R.id.imageView);
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public void setMobile(String mobile) {
        mobileTextView.setText(mobile);
    }

    public void setAge(int age) {
        ageTextView.setText(String.valueOf(age));
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }
}
