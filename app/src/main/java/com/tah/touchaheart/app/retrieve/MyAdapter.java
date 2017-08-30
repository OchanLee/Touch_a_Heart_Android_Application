package com.tah.touchaheart.app.retrieve;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tah.touchaheart.R;

import java.util.ArrayList;

/**
 * Created by Lee on 8/19/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Donations> donationss;

    public MyAdapter(Context c, ArrayList<Donations> donationss) {
        this.c = c;
        this.donationss = donationss;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final Donations d=donationss.get(position);

        holder.nameTxt.setText(d.getDescription());
        PicassoClient.downloadImage(c,d.getImageUrl(),holder.img);

        holder.setItemClicklistener(new ItemClicklistener() {
            @Override
            public void onItemClick() {

                openDetailActivity(d.getType(),d.getRemail(),d.getDescription(),d.getGender(),d.getLocation(),d.getSize(),d.getCondition(),d.getQuantity(),d.getImageUrl());

            }
        });

    }

    @Override
    public int getItemCount() {
        return donationss.size();
    }

    private void openDetailActivity(String dtype, String remail, String description, String rgender, String rlocation, String rsize, String rcondition , String rquantity, String imageUrl){
        Intent i=new Intent(c, DetailActivity.class);

        i.putExtra("TYPE_KEY",dtype);
        i.putExtra("EMAIL_KEY",remail);
        i.putExtra("DESCRIPTION_KEY",description);
        i.putExtra("GENDER_KEY",rgender);
        i.putExtra("LOCATION_KEY",rlocation);
        i.putExtra("SIZE_KEY",rsize);
        i.putExtra("CONDITION_KEY",rcondition);
        i.putExtra("QUANTITY_KEY",rquantity);
        i.putExtra("IMAGEURL_KEY",imageUrl);


        c.startActivity(i);

    }
}
