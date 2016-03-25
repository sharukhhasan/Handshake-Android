package com.sharukhhasan.handshake;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.sharukhhasan.handshake.activities.PastshakesActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sharukh on 1/27/16.
 */
public class ShakeAdapter extends ArrayAdapter<User>
{
    List<User> userList = new ArrayList<User>();
    Context context;
    LayoutInflater inflater;
    String picImgURL;

    public ShakeAdapter(Context context, ArrayList<User> userList)
    {
        super(context, R.layout.shakes_list_row, userList);
        this.userList = userList;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        final User user = userList.get(position);

        View row = inflater.inflate(R.layout.shakes_list_row, parent, false);

        holder.email = (ImageButton) row.findViewById(R.id.emailBtn);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getUserEmail()});
                context.startActivity(emailIntent);
            }
        });

        ImageButton linkedinButton = (ImageButton) row.findViewById(R.id.linkedinBtn);
        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent getintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.android-examples.com"));
                context.startActivity(getintent);
            }
        });

        ImageButton facebookButton = (ImageButton) row.findViewById(R.id.fbBtn);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    // Check if FB app is even installed
                    context.getPackageManager().getPackageInfo("com.facebook.katana", 0);

                    String facebookScheme = "fb://profile/" + user.getUserFacebookId();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
                    context.startActivity(intent);
                } catch(Exception e) {

                    // Cache and Open a url in browser
                    String facebookProfileUri = "https://www.facebook.com/" + user.getUserFacebookId();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUri));
                    context.startActivity(intent);
                }
            }
        });

        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.shakes_list_row, null);

            // get TextView in document_list layout
            holder.user_name = (TextView) convertView.findViewById(R.id.nameTextView);

            // Add and download the image
            holder.userImg = (ImageView) convertView.findViewById(R.id.profile_image);
            picImgURL = userList.get(position).userPictureURL.toString();

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.populate(user, context, ((PastshakesActivity) context).isViewBusy());

        return convertView;
    }

    static class ViewHolder {
        TextView user_name;
        ImageView userImg;
        ImageButton email;
        ImageButton facebook;
        ImageButton linkedin;

        void populate(User user, Context context)
        {
            user_name.setText(user.getUserName());

            Glide.with(context).load(user.getUserPictureURL()).into(userImg);
        }

        void populate(User user, Context context, boolean isBusy)
        {
            if(!isBusy)
            {
                user_name.setText(user.getUserName());
                Glide.with(context).load(user.getUserPictureURL()).into(userImg);
            }
        }
    }
}
