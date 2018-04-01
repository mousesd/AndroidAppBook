package net.homenet.a05_09;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout viewerContainer = this.findViewById(R.id.viewerContainer);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.beach);
        if (bitmap != null) {
            ImageDisplayView viewer = new ImageDisplayView(this);
            viewer.setImageData(bitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);

            viewerContainer.addView(viewer, params);
        }
    }
}
