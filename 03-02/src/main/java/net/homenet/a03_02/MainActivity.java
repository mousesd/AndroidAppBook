package net.homenet.a03_02;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = this.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //# 1.
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivityForResult(intent, Config.REQUEST_MENU_ACTIVITY);

                //# 2.
                ComponentName name = new ComponentName("net.homenet.a03_02", "net.homenet.a03_02.MenuActivity");
                Intent intent = new Intent();
                intent.setComponent(name);
                startActivityForResult(intent, Config.REQUEST_MENU_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, String.format("requestCode: %d", requestCode));
        Log.d(TAG, String.format("resultCode: %d", resultCode));

        if (requestCode == Config.REQUEST_MENU_ACTIVITY) {
            if (resultCode == Config.RESULT_SUCCESSED) {
                Toast.makeText(this, String.format("Name: %s", data.getExtras().getString("name"))
                    , Toast.LENGTH_LONG).show();
            }
        }
    }
}
