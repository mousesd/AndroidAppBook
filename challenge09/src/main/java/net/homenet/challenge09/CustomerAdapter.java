package net.homenet.challenge09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CustomerModel> customers;

    public CustomerAdapter(Context context) {
        this.context = context;
        customers = new ArrayList<>();
    }

    public void addCustomer(CustomerModel customer) {
        customers.add(customer);
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.view_customer, viewGroup, false);

        ImageView iconImageView = view.findViewById(R.id.iconImageView);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView birthdayTextView = view.findViewById(R.id.birthdayTextView);
        TextView telNoTextview = view.findViewById(R.id.telNoTextView);

        CustomerModel customer = customers.get(position);
        iconImageView.setImageResource(customer.getResourceId());
        nameTextView.setText(customer.getName());
        birthdayTextView.setText(customer.getBirthday());
        telNoTextview.setText(customer.getTelNo());
        return view;
    }
}
