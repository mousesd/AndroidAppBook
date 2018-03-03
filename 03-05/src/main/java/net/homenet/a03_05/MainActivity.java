package net.homenet.a03_05;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        this.showToastMessage("MainActivity.onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.showToastMessage("MainActivty.onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.showToastMessage("MainActivty.onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.showToastMessage("MainActivty.onResume()");

        EditText nameEditText = this.findViewById(R.id.nameEditText);
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if (pref != null && pref.contains("name")) {
            String name = pref.getString("name", "");
            nameEditText.setText(name);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.showToastMessage("MainActivty.onPause()");

        EditText nameEditText = this.findViewById(R.id.nameEditText);

        SharedPreferences pref = this.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", nameEditText.getText().toString());
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.showToastMessage("MainActivty.onDestroy()");
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
