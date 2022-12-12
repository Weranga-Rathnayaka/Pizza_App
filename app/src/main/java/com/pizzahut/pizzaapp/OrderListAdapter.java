package com.pizzahut.pizzaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Order> orderList;

    public OrderListAdapter(Context context, int layout, ArrayList<Order> orderList) {
        this.context = context;
        this.layout = layout;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView orderId,productId,qty,address,contactNumber,date;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder =new ViewHolder();
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.orderId = (TextView) row.findViewById(R.id.orderIdView);
            holder.productId = (TextView) row.findViewById(R.id.productIdView);
            holder.qty = (TextView) row.findViewById(R.id.qtyView);
            holder.address = (TextView) row.findViewById(R.id.addressView);
            holder.contactNumber = (TextView) row.findViewById(R.id.contactView);
            holder.date = (TextView) row.findViewById(R.id.dateView);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        Order order = orderList.get(position);
        holder.orderId.setText(Integer.toString(order.getOrderId()));
        holder.productId.setText(order.getProductID());
        holder.qty.setText(order.getQty());
        holder.address.setText(order.getAddress());
        holder.contactNumber.setText(order.getContactNumber());
        holder.date.setText(order.getDate());
        return row;
    }

}
