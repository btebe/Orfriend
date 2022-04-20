package com.orfriend.orfriend.backend.m_Mysql;

import android.content.Context;
import android.widget.ImageView;

import com.orfriend.orfriend.R;
import com.squareup.picasso.Picasso;

/**
 * Created by basmatebe on 1/2/17.
 */

public class PicassoClient {

    public static void downloadImage(Context c, String imageUrl, ImageView img)
    {
        if(imageUrl.length()>0 && imageUrl != null)
        {
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.sam).into(img);
        }else {
            Picasso.with(c).load(R.drawable.sam).into(img);
        }
    }
}
