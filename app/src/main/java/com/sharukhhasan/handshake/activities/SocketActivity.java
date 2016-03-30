package com.sharukhhasan.handshake.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sharukhhasan.handshake.io.Node;
import com.sharukhhasan.handshake.R;


import java.util.Random;


public class SocketActivity extends AppCompatActivity
{
    public static final String[] userInfo = {
            "Sharukh Hasan",
            "hasan.sharukh@gmail.com",
            "10208508462248016",
            "fb://profile/10208508462248016",
            "https://graph.facebook.com/10208508462248016/picture?type=small"
    };

    private TextView peersTextView;
    private TextView framesTextView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView idTextView;
    private TextView urlTextView;

    Node node;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        peersTextView = (TextView) findViewById(R.id.peersTextView);
        framesTextView = (TextView) findViewById(R.id.framesTextView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        idTextView = (TextView) findViewById(R.id.idTextView);
        urlTextView = (TextView) findViewById(R.id.urlTextView);

        node = new Node(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        node.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(node != null)
            node.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static boolean started = false;

    public void sendFrames(View view)
    {
		/*if(!started)
		{
			started = true;
			node = new Node(this);
			node.start();
			return;
		}*/

        node.broadcastFrame(new byte[1]);

        for(int i = 0; i < 2000; ++i)
        {
            byte[] frameData = new byte[1024];
            new Random().nextBytes(frameData);

            node.broadcastFrame(frameData);
        }

		/*for(int i = 0; i < 100; ++i)
		{
			byte[] frameData = new byte[100 * 1024];
			new Random().nextBytes(frameData);
			node.broadcastFrame(frameData);
		}*/
    }

    public void refreshPeers()
    {
        peersTextView.setText(node.getLinks().size() + " connected");
    }

    public void refreshFrames()
    {
        framesTextView.setText(node.getFramesCount() + " frames");
    }

    public void refreshApple()
    {
        nameTextView.setText(node.getName());
        emailTextView.setText(node.getEmail());
        idTextView.setText(node.getId());
        urlTextView.setText(node.getUrl());
    }
}
