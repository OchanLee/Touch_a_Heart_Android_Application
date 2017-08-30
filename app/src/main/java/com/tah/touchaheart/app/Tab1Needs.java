package com.tah.touchaheart.app;


/**
 * Created by Lee on 8/14/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tah.touchaheart.R;
import com.tah.touchaheart.app.retrieve.Downloader;

public class Tab1Needs extends Fragment {
    final static String urlAddress= "http://"+MainTabbedActivity.url_address+"/touchaheartapp/retrieve.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1needs, container, false);

        final RecyclerView rv=(RecyclerView) rootView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout) rootView.findViewById(R.id.refresh);


        new Downloader(getActivity(),urlAddress,rv,swipeRefreshLayout).execute();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Downloader(getActivity(),urlAddress,rv,swipeRefreshLayout).execute();

            }
        });





            return rootView;


    }
}
