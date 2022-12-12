package com.pizzahut.pizzaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Driver_OngoingListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Driver_Ongoing> ongoingslist;

    public Driver_OngoingListAdapter(Context context, int layout, ArrayList<Driver_Ongoing> ongoingslist) {
        this.context = context;
        this.layout = layout;
        this.ongoingslist = ongoingslist;
    }

    @Override
    public int getCount() {
        return ongoingslist.size();
    }

    @Override
    public Object getItem(int position) {
        return ongoingslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtnewdriverid,txtnewdrivername,txtnewdrivernumber,txtnewdriveraddress,txtnewdriveremail,txtnewdrivervehiclemodel,txtnewdrivervehiclenumber,txtnewdriverdob;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();


        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txtnewdriverid = (TextView) row.findViewById(R.id.txtnewdriverid);
            holder.txtnewdrivername = (TextView) row.findViewById(R.id.txtnewdrivername);
            holder.txtnewdrivernumber= (TextView) row.findViewById(R.id.txtnewdrivernumber);
            holder.txtnewdriveraddress = (TextView) row.findViewById(R.id.txtnewdriveraddress);
            holder.txtnewdriveremail = (TextView) row.findViewById(R.id.txtnewdriveremail);
            holder.txtnewdrivervehiclemodel = (TextView) row.findViewById(R.id.txtnewdrivervehiclemodel);
            holder.txtnewdrivervehiclenumber = (TextView) row.findViewById(R.id.txtnewdrivervehiclenumber);
            holder.txtnewdriverdob = (TextView) row.findViewById(R.id.txtnewdriverdob);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Driver_Ongoing ongoing = ongoingslist.get(position);

        holder.txtnewdriverid.setText(ongoing.getNewdriverid());
        holder.txtnewdrivername.setText(ongoing.getNewdrivername());
        holder.txtnewdrivernumber.setText(ongoing.getNewdrivernumber());
        holder.txtnewdriveraddress.setText(ongoing.getNewdriveraddress());
        holder.txtnewdriveremail.setText(ongoing.getNewdriveremail());
        holder.txtnewdrivervehiclemodel.setText(ongoing.getNewdrivervehiclemodel());
        holder.txtnewdrivervehiclenumber .setText(ongoing.getNewdrivervehiclenumber());
        holder.txtnewdriverdob .setText(ongoing.getNewdriverdob());

        return row;
    }


}
