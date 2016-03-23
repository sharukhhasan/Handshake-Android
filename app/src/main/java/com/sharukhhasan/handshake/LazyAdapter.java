package com.sharukhhasan.handshake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sharukh on 1/27/16.
 */
public class LazyAdapter //extends BaseAdapter
{
    /*private List<User> userList = new ArrayList<>();
    private Context context;

    public LazyAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return userList.size();
    }

    @Override
    public User getItem(int arg0)
    {
        return userList.get(arg0);
    }

    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_row, parent, false);
        }

        TextView contactNameTextView = (TextView) convertView.findViewById(R.id.contact_name);

        User user = userList.get(position);
        contactNameTextView.setText(user.userFirstName);

        ImageLoader imageLoader = VolleySingleton.getInstance(context).getImageLoader();
        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.contact_image);
        imageView.setImageUrl(contact.getSmallImageURL(), imageLoader);
        return convertView;
    }

    public List<User> getContactList()
    {
        return userList;
    }

    public void setContactList(List<User> userList)
    {
        this.userList = userList;
    }*/
}
