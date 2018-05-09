package net.homenet.challenge13;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomerView extends LinearLayout {

    private TextView nameTextView;
    private TextView mobileTextView;
    private TextView addressTextView;
    private ImageView imageView;

    public CustomerView(Context context) {
        this(context, null);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null)
            return;
        inflater.inflate(R.layout.view_customer, this, true);

        nameTextView = this.findViewById(R.id.nameTextView);
        mobileTextView = this.findViewById(R.id.mobileTextView);
        addressTextView = this.findViewById(R.id.addressTextView);
        imageView = this.findViewById(R.id.imageView);
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public void setMobile(String mobile) {
        mobileTextView.setText(mobile);
    }

    public void setAddress(String address) {
        addressTextView.setText(address);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }
}
