package com.tah.touchaheart.app.retrieve;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Lee on 8/19/2017.
 */

public class DataParser extends AsyncTask<Void,Void,Boolean> {

    Context c;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    //ProgressDialog pd;
    ArrayList<Donations> donationss = new ArrayList<>();

    public DataParser(Context c, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       /* pd= new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();*/
    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }


    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        swipeRefreshLayout.setRefreshing(false);

        //pd.dismiss();
        if (parsed){

            MyAdapter adapter = new MyAdapter(c,donationss);
            rv.setAdapter(adapter);

        }else {
            Toast.makeText(c,"No Data..Unable to parse",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean parseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            donationss.clear();
            Donations donations;

            for (int i=0;i<ja.length();i++){

                jo=ja.getJSONObject(i);

                int id=jo.getInt("r_id");
                String rtype = jo.getString("rtype");
                String remail = jo.getString("remail");
                String description = jo.getString("rdescription");
                String rgender = jo.getString("rgender");
                String rlocation = jo.getString("rlocation");
                String rquantity = jo.getString("rquantity");
                String rsize = jo.getString("rsize");
                String rcondition = jo.getString("rcondition");
            //    String imageUrl = jo.getString("image_path");     //added to see what happens

                donations= new Donations();

                donations.setId(id);
                donations.setDescription(description);
                donations.setRemail(remail);
                donations.setType(rtype);
                donations.setGender(rgender);
                donations.setlocation(rlocation);
                donations.setQuantity(rquantity);
                donations.setSize(rsize);
                donations.setCondition(rcondition);
             //   donations.setImageUrl(imageUrl);                        //added to see what happens

                donationss.add(donations);


            }


            return true;



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }




}
