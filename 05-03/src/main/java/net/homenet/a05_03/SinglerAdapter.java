package net.homenet.a05_03;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SinglerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SingerItem> singerItems = new ArrayList<>();

    public SinglerAdapter(Context context, ArrayList<SingerItem> singerItems) {
        this.context = context;
        this.singerItems = singerItems;
    }

    @Override
    public int getCount() {
        return singerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return singerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SingerItemView itemView;
        if (view == null) {
            itemView = new SingerItemView(this.context);
        } else {
            itemView = (SingerItemView) view;
        }

        SingerItem singerItem = singerItems.get(i);
        itemView.setName(singerItem.getName());
        itemView.setMobile(singerItem.getMobile());
        itemView.setAge(singerItem.getAge());
        itemView.setImage(singerItem.getImageResId());
        return itemView;
    }
}
