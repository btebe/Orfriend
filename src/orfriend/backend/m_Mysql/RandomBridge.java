package com.orfriend.orfriend.backend.m_Mysql;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.MySQLClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import static com.orfriend.orfriend.SessionManager.KEY_USER_ID;

/**
 * Created by basmatebe on 1/6/17.
 */

public class RandomBridge {

    private ArrayList<Children> mDataSource;
    private Context mContext;
    private Activity activity;
    private Random randomGenerator;
    Children child;
    SessionManager session;


    public RandomBridge (Context context, ArrayList<Children> items, Activity x) {
        mContext = context;
        mDataSource = items;
        activity = x;
    }

    public void  showRandom(){


    }
    public void dialog(final String listType){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.one_item);
        dialog.setTitle("Custom Dialog Example");
        TextView titleTextView =
                (TextView) dialog.findViewById(com.orfriend.orfriend.R.id.title2);
        TextView subtitleTextView =
                (TextView) dialog.findViewById(com.orfriend.orfriend.R.id.subtitle2);
        TextView detailTextView =
                (TextView) dialog.findViewById(com.orfriend.orfriend.R.id.detail2);
        ImageView thumbnailImageView =
                (ImageView) dialog.findViewById(com.orfriend.orfriend.R.id.pic);
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.dismissbtn);
        Button dialogButtonAdd = (Button) dialog.findViewById(R.id.addbtn);

        //final Children child = mDataSource.get(new Random().nextInt(mDataSource.size()));
        int index = (int)((double)mDataSource.size() * Math.random());
        final Children child = mDataSource.get(index);
        titleTextView.setText(child.getName());
        subtitleTextView.setText(child.getHobby());
        detailTextView.setText((child.getAge()));
        Picasso.with(mContext).load(child.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Your android custom dialog ok action
        // Action for custom dialog ok button click
        dialogButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String listtype = "MyChildrenList";

                //Toast.makeText(ListViewPage.this, Integer.toString(selectedChild.getId()), Toast.LENGTH_LONG).show();
                session = new SessionManager(mContext);
                //Toast.makeText(ListViewPage.this, Integer.toString(selectedChild.getId()), Toast.LENGTH_LONG).show();
                //new MySQLClient(mContext.this).add(mContext, listtype,session.getUserDetails().get(KEY_USER_ID));
                new MySQLClient(mContext).add(child, listType,session.getUserDetails().get(KEY_USER_ID));
                dialog.dismiss();
            }
        });
        dialog.show();


    }




}
