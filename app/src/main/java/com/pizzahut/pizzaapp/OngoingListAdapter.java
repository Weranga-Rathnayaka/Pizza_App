package com.pizzahut.pizzaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OngoingListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Ongoing> ongoingslist;

    public OngoingListAdapter(Context context, int layout, ArrayList<Ongoing> ongoingslist) {
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
        TextView txtedtoderid,txtedtname,txtedtnumber,txtedtaddress,txtedtprice,txtedtdriverid,txtedtdrivername,txtedtcomplete;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();


        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txtedtoderid = (TextView) row.findViewById(R.id.txtedtoderid);
            holder.txtedtname = (TextView) row.findViewById(R.id.txtedtname);
            holder.txtedtnumber= (TextView) row.findViewById(R.id.txtedtnumber);
            holder.txtedtaddress = (TextView) row.findViewById(R.id.txtedtaddress);
            holder.txtedtprice = (TextView) row.findViewById(R.id.txtedtprice);
            holder.txtedtdriverid = (TextView) row.findViewById(R.id.txtedtdriverid);
            holder.txtedtdrivername = (TextView) row.findViewById(R.id.txtedtdrivername);
            holder.txtedtcomplete = (TextView) row.findViewById(R.id.txtedtcomplete);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Ongoing ongoing = ongoingslist.get(position);

        holder.txtedtoderid.setText(ongoing.getEdtoderid());
        holder.txtedtname.setText(ongoing.getEdtname());
        holder.txtedtnumber.setText(ongoing.getEdtnumber());
        holder.txtedtaddress.setText(ongoing.getEdtaddress());
        holder.txtedtprice.setText(ongoing.getEdtprice());
        holder.txtedtdriverid.setText(ongoing.getEdtdriverid());
        holder.txtedtdrivername .setText(ongoing.getEdtdrivername());
        holder.txtedtcomplete .setText(ongoing.getEdtcomplete());

        return row;
    }
}

