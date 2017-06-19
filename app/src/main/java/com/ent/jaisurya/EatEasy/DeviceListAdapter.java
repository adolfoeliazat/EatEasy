package com.ent.jaisurya.EatEasy;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ent.jaisurya.EatEasy.R;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class DeviceListAdapter extends ArrayAdapter<DeviceItem>{

    private Context context;
    private BluetoothAdapter bTAdapter;

    public DeviceListAdapter(Context context, List items, BluetoothAdapter bTAdapter) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.bTAdapter = bTAdapter;
        this.context = context;
    }


    private class ViewHolder{
        TextView titleText;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View line = null;
        DeviceItem item = (DeviceItem)getItem(position);
        final String name = item.getDeviceName();
        TextView macAddress = null;
        View viewToUse = null;


        //Grid or view

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        viewToUse = mInflater.inflate(R.layout.device_list_item, null);
        holder = new ViewHolder();
        holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
        viewToUse.setTag(holder);

        macAddress = (TextView)viewToUse.findViewById(R.id.macAddress);
        line = (View)viewToUse.findViewById(R.id.line);
        holder.titleText.setText(item.getDeviceName());
        macAddress.setText(item.getAddress());
        Button menu = (Button)viewToUse.findViewById(R.id.menu);


        if ( item.getDeviceName().toString() == "No Devices") {
            macAddress.setVisibility(View.INVISIBLE);
            line.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT, (int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            holder.titleText.setLayoutParams(params);
        }

        return viewToUse;
    }


}
