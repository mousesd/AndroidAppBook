package net.homenet.challenge09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView customerCountTextView = this.findViewById(R.id.personCountTextView);
        final EditText nameEditText = this.findViewById(R.id.nameEditText);
        final EditText birthdayEditText = this.findViewById(R.id.birthdayEditText);
        final EditText telNoEditText = this.findViewById(R.id.telNoEditText);

        Button addButton = this.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty())
                    return;

                if (birthdayEditText.getText().toString().isEmpty())
                    return;

                if (telNoEditText.getText().toString().isEmpty())
                    return;

                CustomerModel customer = new CustomerModel(R.drawable.customer
                    , nameEditText.getText().toString()
                    , birthdayEditText.getText().toString()
                    , telNoEditText.getText().toString());
                adapter.addCustomer(customer);
                adapter.notifyDataSetChanged();
                customerCountTextView.setText(String.format("%s Persons",String.valueOf(adapter.getCount())));
            }
        });

        adapter = new CustomerAdapter(this);
        ListView listView = this.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
