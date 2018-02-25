package net.homenet.challenge03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView upImageView;
    private ImageView downImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upImageView = this.findViewById(R.id.upImageView);
        downImageView = this.findViewById(R.id.downImageView);
    }

    public void onUpClick(View view) {
        upImageView.setVisibility(View.VISIBLE);
        downImageView.setVisibility(View.INVISIBLE);
    }

    public void onDownClick(View view) {
        upImageView.setVisibility(View.INVISIBLE);
        downImageView.setVisibility(View.VISIBLE);
    }
}
