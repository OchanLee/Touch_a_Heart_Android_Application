package com.tah.touchaheart.app.retrieve;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tah.touchaheart.R;

/**
 * Created by Lee on 8/20/2017.
 */

public class PicassoClient {

    public static void downloadImage(Context c, String imageUrl, ImageView img){

        if (imageUrl !=null && imageUrl.length()>0){
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.clothes).into(img);

        }else {
            Picasso.with(c).load(R.drawable.clothes).into(img);
        }
    }
}
