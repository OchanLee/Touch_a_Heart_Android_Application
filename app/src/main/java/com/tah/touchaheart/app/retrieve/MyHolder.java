package com.tah.touchaheart.app.retrieve;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tah.touchaheart.R;

/**
 * Created by Lee on 8/19/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt;
    ImageView img;
    ItemClicklistener itemClicklistener;



    public MyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.txt1donate);
        img= (ImageView) itemView.findViewById(R.id.donateImage);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClicklistener.onItemClick();

    }

    public void setItemClicklistener(ItemClicklistener itemClicklistener){

        this.itemClicklistener=itemClicklistener;
    }
}
