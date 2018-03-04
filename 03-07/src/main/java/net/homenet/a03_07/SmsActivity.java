package net.homenet.a03_07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    private EditText senderEditText;
    private EditText contentEditText;
    private EditText receivedDateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        Button backButton = this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        senderEditText = this.findViewById(R.id.senderEditText);
        contentEditText = this.findViewById(R.id.contentEditText);
        receivedDateEditText = this.findViewById(R.id.receivedDateEditText);

        this.processIntent(this.getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        this.processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent == null)
            return;

        String sender = intent.getStringExtra("sender");
        String content = intent.getStringExtra("content");
        String receivedDate = intent.getStringExtra("receivedDate");

        senderEditText.setText(sender);
        contentEditText.setText(content);
        receivedDateEditText.setText(receivedDate);
    }
}
