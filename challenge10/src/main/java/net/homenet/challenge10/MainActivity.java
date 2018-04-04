package net.homenet.challenge10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel(R.drawable.clothes1, "Product #01", 1000, "Product #01"));
        products.add(new ProductModel(R.drawable.clothes2, "Product #02", 2000, "Product #02"));
        products.add(new ProductModel(R.drawable.clothes3, "Product #03", 3000, "Product #03"));
        products.add(new ProductModel(R.drawable.clothes4, "Product #04", 4000, "Product #04"));
        products.add(new ProductModel(R.drawable.clothes5, "Product #05", 5000, "Product #05"));
        products.add(new ProductModel(R.drawable.clothes6, "Product #06", 6000, "Product #06"));
        products.add(new ProductModel(R.drawable.clothes7, "Product #07", 7000, "Product #07"));
        products.add(new ProductModel(R.drawable.clothes8, "Product #08", 8000, "Product #08"));
        adapter = new ProductAdapter(this, products);

        GridView gridView = this.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ProductModel product = (ProductModel) adapter.getItem(position);
                Toast.makeText(MainActivity.this, String.format("Name: %s, Price: %s, Description: %s"
                    , product.getName(), String.valueOf(product.getPrice()), product.getDescription())
                    , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
