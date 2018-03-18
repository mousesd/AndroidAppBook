package net.homenet.a04_12;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = this.findViewById(R.id.textView);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle("Sub Title");
        }

        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setLogo(R.drawable.home);
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                textView.setText(R.string.refresh);
                break;
            case R.id.menu_search:
                textView.setText(R.string.search);
                break;
            case R.id.menu_settings:
                textView.setText(R.string.settings);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
