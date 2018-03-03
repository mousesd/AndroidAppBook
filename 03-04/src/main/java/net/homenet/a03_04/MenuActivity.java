package net.homenet.a03_04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView textView = this.findViewById(R.id.textView);
        Button backButton = this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "Sangdae");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Intent receiveIntent = this.getIntent();
        if (receiveIntent == null)
            return;

        Bundle bundle = receiveIntent.getExtras();
        if (bundle == null)
            return;

        SimpleData data = bundle.getParcelable(Config.KEY_SIMPLE_DATA);
        if (data != null) {
            textView.setText(String.format("Receive data\nNumber: %d\nMessage: %s"
                , data.getNumber(), data.getMessage()));
        }
    }
}
