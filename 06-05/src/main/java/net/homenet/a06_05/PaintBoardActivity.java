package net.homenet.a06_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaintBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(new PaintBoard(this));
    }
}
