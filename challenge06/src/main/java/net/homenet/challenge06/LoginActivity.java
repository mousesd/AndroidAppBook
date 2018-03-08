package net.homenet.challenge06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idEditText = this.findViewById(R.id.idEditText);
        final EditText passwordEditText = this.findViewById(R.id.passwordEditText);
        Button loginButton = this.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (id.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Input the ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Input the Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Intent intent = this.getIntent();
        if (intent != null)
            this.onNewIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent == null)
            return;

        String activityName = intent.getStringExtra("activityName");
        Toast.makeText(this, String.format("ActivityName: %S", activityName), Toast.LENGTH_SHORT).show();
    }
}
