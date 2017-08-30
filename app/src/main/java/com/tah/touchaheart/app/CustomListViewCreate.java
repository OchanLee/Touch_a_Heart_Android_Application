package com.tah.touchaheart.app;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tah.touchaheart.R;

/**
 * Created by Lee on 8/14/2017.
 */

public class CustomListViewCreate extends ArrayAdapter<String> {

    private String[] itemname;
    private String[] desc;
    private Integer[] imgid;
    private Tab3Create context;

    public CustomListViewCreate(Tab3Create context, String[] itemname, String[] desc, Integer[] imgid) {
        super(context.getActivity(), R.layout.listviewcreatelayout, itemname);

        this.context=context;
        this.itemname=itemname;
        this.desc=desc;
        this.imgid=imgid;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r=convertView;
        ViewHolder viewHolder=null;
        if (r==null){

            LayoutInflater layoutInflater=context.getActivity().getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listviewcreatelayout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);

        }else {

            viewHolder=(ViewHolder) r.getTag();

        }

        viewHolder.ivw.setImageResource(imgid[position]);

        viewHolder.tvwl.setText(itemname[position]);
        viewHolder.tvw2.setText(desc[position]);

        return r;



    }
    class ViewHolder{

        TextView tvwl;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvwl =(TextView) v.findViewById(R.id.itemname);
            tvw2=(TextView) v.findViewById(R.id.itemdescription);
            ivw=(ImageView) v.findViewById(R.id.imageViewCreate);


        }

    }
}
