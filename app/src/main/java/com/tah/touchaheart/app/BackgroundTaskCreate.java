package com.tah.touchaheart.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.tah.touchaheart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Lee on 8/9/2017.
 */

public class BackgroundTaskCreate extends AsyncTask<String,Void,String> {
    String clothessubmit_url = "http://"+MainTabbedActivity.url_address+"/touchaheartapp/create.php";
    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    public BackgroundTaskCreate(Context ctx)
    {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Please Wait..be Patient");
        progressDialog.setMessage("Connecting to Server");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];

        if (method.equals("clothessubmit"))
        {
            try {
                URL url = new URL(clothessubmit_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String remail = params[1];
                String rdescription = params[2];
                String rtype = params[3];
                String rgender = params[4];
                String rquantity = params[5];
                String rsize = params[6];
                String rcondition = params[7];
                String rlocation = params[8];
                String data = URLEncoder.encode("remail", "UTF-8")+"="+URLEncoder.encode(remail,"UTF-8")+"&"+
                        URLEncoder.encode("rdescription", "UTF-8")+"="+URLEncoder.encode(rdescription,"UTF-8")+"&"+
                        URLEncoder.encode("rtype", "UTF-8")+"="+URLEncoder.encode(rtype,"UTF-8")+"&"+
                        URLEncoder.encode("rgender", "UTF-8")+"="+URLEncoder.encode(rgender,"UTF-8")+"&"+
                        URLEncoder.encode("rquantity", "UTF-8")+"="+URLEncoder.encode(rquantity,"UTF-8")+"&"+
                        URLEncoder.encode("rsize", "UTF-8")+"="+URLEncoder.encode(rsize,"UTF-8")+"&"+
                        URLEncoder.encode("rcondition", "UTF-8")+"="+URLEncoder.encode(rcondition,"UTF-8")+"&"+
                        URLEncoder.encode("rlocation", "UTF-8")+"="+URLEncoder.encode(rlocation,"UTF-8")+"&";
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line=bufferedReader.readLine())!= null)
                {

                    stringBuilder.append(line+"\n");
                }

                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");
                return  stringBuilder.toString().trim();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {

        try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            if (code.equals("create_true")){

                Toast.makeText(ctx, "Need Created Successfully. ", Toast.LENGTH_LONG).show();


            }else if (code.equals("create_false")){

                showDialog("Failed!",message,code);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void showDialog (String title, String message, String code){

        builder.setTitle(title);
        if (code.equals("create_true") || code.equals("create_false")){

            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    activity.finish();

                }
            });


        }


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
