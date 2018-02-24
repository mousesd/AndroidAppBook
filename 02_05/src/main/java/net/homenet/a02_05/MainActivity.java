package net.homenet.a02_05;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollView scrollView = this.findViewById(R.id.scrollView);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = this.getResources();
        BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView = this.findViewById(R.id.imageView);
        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onChangeImageClick(View view) {
        this.changeImage();
    }

    private void changeImage() {
        Resources res = this.getResources();
        BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }
}
