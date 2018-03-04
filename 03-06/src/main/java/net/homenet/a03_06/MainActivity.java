package net.homenet.a03_06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = MainActivity.class.getName();
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = this.findViewById(R.id.nameEditText);
        Button sendServiceButton = this.findViewById(R.id.sendServiceButton);
        sendServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name);
                startService(intent);
            }
        });

        Intent intent = this.getIntent();
        if (intent != null)
            this.processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent != null)
            this.processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent == null)
            return;

        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        nameEditText.setText(name);
        Log.d(Tag, String.format("command: %s, name: %s", command, name));
    }
}
