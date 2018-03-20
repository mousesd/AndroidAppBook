package net.homenet.a04_14;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private FirstTabFragment fragment1;
    private SecondTabFragment fragment2;
    private ThirdTabFragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        fragment1 = new FirstTabFragment();
        fragment2 = new SecondTabFragment();
        fragment3 = new ThirdTabFragment();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        TabLayout tabs = this.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Call history"));
        tabs.addTab(tabs.newTab().setText("Spam history"));
        tabs.addTab(tabs.newTab().setText("Contacts"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d(TAG, "SelectedTab: " + position);

                Fragment selected = null;
                switch (position) {
                    case 0:
                        selected = fragment1;
                        break;
                    case 1:
                        selected = fragment2;
                        break;
                    case 2:
                        selected = fragment3;
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
