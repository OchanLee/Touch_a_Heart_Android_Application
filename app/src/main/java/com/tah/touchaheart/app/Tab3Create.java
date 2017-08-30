package com.tah.touchaheart.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tah.touchaheart.R;

/**
 * Created by Lee on 8/14/2017.
 */

public class Tab3Create extends Fragment {

    //added by lee rogers

    ListView lst;
    String[] itemname={"Clothes","Shoes"};
    String[] desc={"Please click to choose clothes", "Please click to choose Shoes"};
    Integer[] imgid={R.drawable.clothes ,R.drawable.shoes};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3create, container, false);

        ///added by lee rogers

        lst = (ListView) rootView.findViewById(R.id.listViewCreate);
        CustomListViewCreate  customListViewCreate=new CustomListViewCreate(this,itemname,desc,imgid);
        lst.setAdapter(customListViewCreate);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        Intent clothesActivity=new Intent(getActivity(), ClothesActivity.class);
                        startActivity(clothesActivity);

                        break;
                    case 1:
                        Intent shoesActivity=new Intent(getActivity(), ShoesActivity.class);
                        startActivity(shoesActivity);

                        break;
                    default:
                        break;

                }

            }
        });

        return rootView;
    }


}

