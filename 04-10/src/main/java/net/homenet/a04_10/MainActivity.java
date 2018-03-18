package net.homenet.a04_10;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback {

    private final int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};
    private ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
    }

    @Override
    public void onImageSelected(int position) {
        if (viewerFragment != null)
            viewerFragment.setImage(images[position]);
    }
}
