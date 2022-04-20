package com.orfriend.orfriend.backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.backend.m_Mysql.PicassoClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by basmatebe on 1/2/17.
 */

public class DbAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Children> mDataSource;

    public DbAdapter (Context context, ArrayList<Children> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item, parent, false);

        TextView titleTextView =
                (TextView) rowView.findViewById(com.orfriend.orfriend.R.id.recipe_list_title);
        TextView subtitleTextView =
                (TextView) rowView.findViewById(com.orfriend.orfriend.R.id.recipe_list_subtitle);
        TextView detailTextView =
                (TextView) rowView.findViewById(com.orfriend.orfriend.R.id.recipe_list_detail);
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(com.orfriend.orfriend.R.id.recipe_list_thumbnail);
        Children child = (Children) getItem(position);

        titleTextView.setText(child.getName());
        subtitleTextView.setText(child.getHobby());
        detailTextView.setText((child.getAge()));

        Picasso.with(mContext).load(child.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
        //PicassoClient.downloadImage(mContext,child.getImageUrl(),thumbnailImageView);
        //detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.hashCode()));
        return rowView;
    }


}
