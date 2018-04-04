package net.homenet.challenge10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductModel> products;

    public ProductAdapter(Context context, ArrayList<ProductModel> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.view_product, viewGroup, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView priceTextView = view.findViewById(R.id.priceTextView);
        TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);

        ProductModel product = products.get(position);
        imageView.setImageResource(product.getResourceId());
        nameTextView.setText(product.getName());
        priceTextView.setText(String.valueOf(product.getPrice()));
        descriptionTextView.setText(product.getDescription());
        return view;
    }
}
