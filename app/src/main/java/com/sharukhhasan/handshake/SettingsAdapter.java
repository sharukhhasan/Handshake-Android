package com.sharukhhasan.handshake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

import com.rey.material.widget.Switch;

/**
 * Created by Sharukh on 3/26/16.
 */
public class SettingsAdapter extends BaseAdapter{
    private List<ViewHolder> settingsList = new ArrayList<>();
    private Context context;

    public SettingsAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return settingsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return settingsList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.profile_row, parent, false);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView textView;
        EditText editText;
        Switch _switch;
    }
}
