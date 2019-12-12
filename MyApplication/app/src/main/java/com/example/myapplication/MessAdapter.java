//自定义适配器
package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessAdapter extends ArrayAdapter<Mess> {
    private int resourceID;
    public MessAdapter(Context context, int textViewResourceID, List<Mess> objects)
    {
        super(context,textViewResourceID,objects);
        resourceID=textViewResourceID;
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent)
    {
        Mess mess =getItem(position);
        View view;
        if(converView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
        }
        else {
            view = converView;
        }
        ImageView Icon = (ImageView) view.findViewById(R.id.icon);
        TextView name=(TextView)view.findViewById(R.id._name);
        TextView number=(TextView)view.findViewById(R.id._number);
        Icon.setImageResource(mess.getIconId());
        name.setText(mess.getName());
        number.setText(mess.getNumber());
        return view;
    }



}
