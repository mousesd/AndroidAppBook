package net.homenet.a04_13;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        View view = menu.findItem(R.id.menu_search).getActionView();
        if (view != null) {
            final EditText editText = view.findViewById(R.id.editText);
            if (editText != null) {
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                            String searchString = editText.getEditableText().toString();
                            Toast.makeText(MainActivity.this, searchString, Toast.LENGTH_SHORT).show();
                        }

                        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        if (inputManager != null)
                            inputManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                        return true;
                    }
                });
            }
        }
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
