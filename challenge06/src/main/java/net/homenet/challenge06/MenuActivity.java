package net.homenet.challenge06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private static final int CUSTOMER_REQUEST_CODE = 2001;
    private static final int GOOD_REQUEST_CODE = 2002;
    private static final int PRODUCT_REQUEST_CODE = 2003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button customerButton = this.findViewById(R.id.customerButton);
        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivityForResult(intent, CUSTOMER_REQUEST_CODE);
            }
        });

        Button goodButton = this.findViewById(R.id.goodButton);
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GoodActivity.class);
                startActivityForResult(intent, GOOD_REQUEST_CODE);
            }
        });

        Button productButton = this.findViewById(R.id.productButton);
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                startActivityForResult(intent, PRODUCT_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != CUSTOMER_REQUEST_CODE && requestCode != GOOD_REQUEST_CODE
            && requestCode != PRODUCT_REQUEST_CODE)
            return;

        if (resultCode != RESULT_OK)
            return;

        Bundle bundle = data.getExtras();
        if (bundle == null)
            return;

        String activityName = bundle.getString("activityName");
        Toast.makeText(this, String.format("ActivityName: %s", activityName)
            , Toast.LENGTH_SHORT).show();
    }
}
