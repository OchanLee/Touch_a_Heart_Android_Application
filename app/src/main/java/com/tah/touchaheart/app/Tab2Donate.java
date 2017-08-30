package com.tah.touchaheart.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tah.touchaheart.R;

/**
 * Created by Lee on 8/14/2017.
 */

public class Tab2Donate extends Fragment {

    ListView lst;
    String[] itemname={"Clothes","Shoes"};
    String[] desc={"Please click to choose clothes", "Please click to choose Shoes"};
    Integer[] imgid={R.drawable.clothes ,R.drawable.shoes};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2donate, container, false);


        lst = (ListView) rootView.findViewById(R.id.listViewDonate);
        CustomListViewDonate  customListViewDonate=new CustomListViewDonate(this,itemname,desc,imgid);
        lst.setAdapter(customListViewDonate);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        Intent clothesDonateActivity=new Intent(getActivity(), ClothesDonateActivity.class);
                        startActivity(clothesDonateActivity);

                        break;
                    case 1:
                        Intent shoesDonateActivity=new Intent(getActivity(), ShoesDonateActivity.class);
                        startActivity(shoesDonateActivity);

                        break;
                    default:
                        break;

                }

            }
        });






        return rootView;
    }
}

