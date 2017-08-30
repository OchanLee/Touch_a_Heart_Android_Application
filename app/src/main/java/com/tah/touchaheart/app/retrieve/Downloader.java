package com.tah.touchaheart.app.retrieve;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Lee on 8/19/2017.
 */

public class Downloader extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (!swipeRefreshLayout.isRefreshing()){

            swipeRefreshLayout.setRefreshing(true);
        }

       /* pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving...Please wait");
        pd.show();*/

    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        //pd.dismiss();

        if (jsonData==null)
        {
           swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(c,"Unsuccesfull, No data retrieved",Toast.LENGTH_SHORT).show();
        }else {

        new DataParser(c,jsonData,rv,swipeRefreshLayout).execute();
        }
    }
    private String downloadData(){
        HttpURLConnection con= Connector.connect(urlAddress);
        if (con==null){
            return null;
        }


        try {
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData= new StringBuffer();

            while ((line=br.readLine()) != null){
                jsonData.append(line+"\n");
            }

            br.close();
            is.close();
            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
