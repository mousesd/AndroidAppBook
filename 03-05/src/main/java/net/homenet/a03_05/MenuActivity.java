package net.homenet.a03_05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button backButton = this.findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.showToastMessage("MenuActivity.onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.showToastMessage("MenuActivity.onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.showToastMessage("MenuActivity.onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.showToastMessage("MenuActivity.onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.showToastMessage("MenuActivity.onDestroy()");
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
