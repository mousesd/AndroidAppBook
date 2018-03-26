package net.homenet.a05_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<SingerItem> singerItems;

    public MainActivity() {
        singerItems = new ArrayList<>();
        singerItems.add(new SingerItem("소녀시대", "010-0000-0000", 20, R.drawable.singer));
        singerItems.add(new SingerItem("걸스데이", "010-0000-0000", 22, R.drawable.singer2));
        singerItems.add(new SingerItem("여자친구", "010-0000-0000", 21, R.drawable.singer3));
        singerItems.add(new SingerItem("티아라", "010-0000-0000", 24, R.drawable.singer4));
        singerItems.add(new SingerItem("AOA", "010-0000-0000", 25, R.drawable.singer5));
        singerItems.add(new SingerItem("소녀시대", "010-0000-0000", 20, R.drawable.singer));
        singerItems.add(new SingerItem("걸스데이", "010-0000-0000", 22, R.drawable.singer2));
        singerItems.add(new SingerItem("여자친구", "010-0000-0000", 21, R.drawable.singer3));
        singerItems.add(new SingerItem("티아라", "010-0000-0000", 24, R.drawable.singer4));
        singerItems.add(new SingerItem("AOA", "010-0000-0000", 25, R.drawable.singer5));
        singerItems.add(new SingerItem("소녀시대", "010-0000-0000", 20, R.drawable.singer));
        singerItems.add(new SingerItem("걸스데이", "010-0000-0000", 22, R.drawable.singer2));
        singerItems.add(new SingerItem("여자친구", "010-0000-0000", 21, R.drawable.singer3));
        singerItems.add(new SingerItem("티아라", "010-0000-0000", 24, R.drawable.singer4));
        singerItems.add(new SingerItem("AOA", "010-0000-0000", 25, R.drawable.singer5));
        singerItems.add(new SingerItem("소녀시대", "010-0000-0000", 20, R.drawable.singer));
        singerItems.add(new SingerItem("걸스데이", "010-0000-0000", 22, R.drawable.singer2));
        singerItems.add(new SingerItem("여자친구", "010-0000-0000", 21, R.drawable.singer3));
        singerItems.add(new SingerItem("티아라", "010-0000-0000", 24, R.drawable.singer4));
        singerItems.add(new SingerItem("AOA", "010-0000-0000", 25, R.drawable.singer5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = this.findViewById(R.id.gridView);
        gridView.setAdapter(new SinglerAdapter(this, singerItems));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinglerAdapter adapter = (SinglerAdapter) adapterView.getAdapter();
                SingerItem singerItem = (SingerItem) adapter.getItem(i);
                Log.d(TAG, String.format("Name: %s, Mobile: %s, Age: %s", singerItem.getName()
                        , singerItem.getMobile(), singerItem.getAge()));
            }
        });
    }
}
