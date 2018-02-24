package net.homenet.a02_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onVerticalClick(View view) {
        Intent intent = new Intent(this.getApplicationContext(), VerticalOrientationActivity.class);
        this.startActivity(intent);
    }

    public void onHorizontalClick(View view) {
        Intent intent = new Intent(this.getApplicationContext(), HorizontalOrientationActivity.class);
        this.startActivity(intent);
    }

    public void onCodeClick(View view) {
        Intent intent = new Intent(this.getApplicationContext(), LayoutCodeActivity.class);
        this.startActivity(intent);
    }

    public void onGravityClick(View view) {
        Intent intent = new Intent(this.getApplicationContext(), GravitySampleActivity.class);
        this.startActivity(intent);
    }

    public void onBaselineAlignedClick(View view) {
        Intent intent = new Intent(this.getApplicationContext(), BaselineAlignedSampleActivity.class);
        this.startActivity(intent);
    }
}
