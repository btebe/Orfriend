package com.orfriend.orfriend.backend.m_Mysql;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.backend.Children;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by basmatebe on 1/5/17.
 */

public class Bridge {

    private ArrayList<User> mDataSource;
    private Context mContext;
    private Activity activity;


    public Bridge (Context context, ArrayList<User> items, Activity x) {
        mContext = context;
        mDataSource = items;
        activity = x;
    }

    public void showPic(){

        User x = mDataSource.get(0);
        CircleImageView pic = (CircleImageView) activity.findViewById(R.id.userPic);
        Picasso.with(mContext).load(x.getPic()).placeholder(com.orfriend.orfriend.R.mipmap.ic_launcher).into(pic);
        TextView name = (TextView) activity.findViewById(R.id.nameField);
        TextView email = (TextView) activity.findViewById(R.id.emailField);
        TextView age = (TextView) activity.findViewById(R.id.ageField);
        TextView job = (TextView) activity.findViewById(R.id.jobField);
        TextView hobby = (TextView) activity.findViewById(R.id.hobbyField);
        TextView nationality = (TextView) activity.findViewById(R.id.nationalityField);
        TextView status = (TextView) activity.findViewById(R.id.statusField);
        name.setText("Name:  "+x.getUsernameOrEmail()+" "+ x.getLname());
        email.setText("Email:  "+x.getEmail());
        age.setText("Age:  "+String.valueOf(x.getAge()));
        job.setText("Job:  "+x.getJob());
        hobby.setText("Hobby:  "+x.getHobby());
        nationality.setText("Nationality:  "+x.getNationality());
        status.setText("Status:  "+x.getStatus());

    }





}
