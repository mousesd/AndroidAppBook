package net.homenet.a04_10;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {
    private final String[] values = {"First image", "Second image", "Third image"};
    private ImageSelectionCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ImageSelectionCallback)
            this.callback = (ImageSelectionCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext()
            , android.R.layout.simple_list_item_1, values);

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (callback != null)
                    callback.onImageSelected(i);
            }
        });

        return view;
    }

    public interface ImageSelectionCallback {
        void onImageSelected(int position);
    }
}
