package net.homenet.a02_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView1;
    private ImageView imageView2;
    private int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = this.findViewById(R.id.imageView1);
        imageView2 = this.findViewById(R.id.imageView2);
    }

    public void onChangeImageClick(View view) {
        if (imageIndex == 0) {
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex = 1;
        } else {
            imageView1.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex = 0;
        }
    }
}
