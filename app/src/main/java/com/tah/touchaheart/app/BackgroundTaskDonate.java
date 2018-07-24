package com.tah.touchaheart.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Lee on 8/19/2017.
 */

public class BackgroundTaskDonate extends AsyncTask<String,Void,String>{
    String clothessubmit_url = "http://"+MainTabbedActivity.url_address+"/touchaheartapp/donate2.php";
    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    public BackgroundTaskDonate(Context ctx)
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

        if (method.equals("clothesdonate"))
        {
            try {
                URL url = new URL(clothessubmit_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String r_email = params[1];
                String d_email = params[2];
                String d_type = params[3];
                String d_gender = params[4];
                String d_quantity = params[5];
                String d_size = params[6];
                String d_condition = params[7];
                String d_location = params[8];
                String encoded_string = params[9];
                String image_name = params[10];
                String data = URLEncoder.encode("r_email", "UTF-8")+"="+URLEncoder.encode(r_email,"UTF-8")+"&"+
                        URLEncoder.encode("d_email", "UTF-8")+"="+URLEncoder.encode(d_email,"UTF-8")+"&"+
                        URLEncoder.encode("d_type", "UTF-8")+"="+URLEncoder.encode(d_type,"UTF-8")+"&"+
                        URLEncoder.encode("d_gender", "UTF-8")+"="+URLEncoder.encode(d_gender,"UTF-8")+"&"+
                        URLEncoder.encode("d_quantity", "UTF-8")+"="+URLEncoder.encode(d_quantity,"UTF-8")+"&"+
                        URLEncoder.encode("d_size", "UTF-8")+"="+URLEncoder.encode(d_size,"UTF-8")+"&"+
                        URLEncoder.encode("d_condition", "UTF-8")+"="+URLEncoder.encode(d_condition,"UTF-8")+"&"+
                        URLEncoder.encode("d_location", "UTF-8")+"="+URLEncoder.encode(d_location,"UTF-8")+"&"+
                        URLEncoder.encode("encoded_string", "UTF-8")+"="+URLEncoder.encode(encoded_string,"UTF-8")+"&"+
                        URLEncoder.encode("image_name", "UTF-8")+"="+URLEncoder.encode(image_name,"UTF-8")+"&";
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





        if (method.equals("clothesdonateNoImage"))
        {
            try {
                URL url = new URL(clothessubmit_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String r_email = params[1];
                String d_email = params[2];
                String d_type = params[3];
                String d_gender = params[4];
                String d_quantity = params[5];
                String d_size = params[6];
                String d_condition = params[7];
                String d_location = params[8];

                String data = URLEncoder.encode("r_email", "UTF-8")+"="+URLEncoder.encode(r_email,"UTF-8")+"&"+
                        URLEncoder.encode("d_email", "UTF-8")+"="+URLEncoder.encode(d_email,"UTF-8")+"&"+
                        URLEncoder.encode("d_type", "UTF-8")+"="+URLEncoder.encode(d_type,"UTF-8")+"&"+
                        URLEncoder.encode("d_gender", "UTF-8")+"="+URLEncoder.encode(d_gender,"UTF-8")+"&"+
                        URLEncoder.encode("d_quantity", "UTF-8")+"="+URLEncoder.encode(d_quantity,"UTF-8")+"&"+
                        URLEncoder.encode("d_size", "UTF-8")+"="+URLEncoder.encode(d_size,"UTF-8")+"&"+
                        URLEncoder.encode("d_condition", "UTF-8")+"="+URLEncoder.encode(d_condition,"UTF-8")+"&"+
                        URLEncoder.encode("d_location", "UTF-8")+"="+URLEncoder.encode(d_location,"UTF-8")+"&";
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

                showDialog("Donated Successfully",message,code);




            }else if (code.equals("create_false")){

                showDialog("Failed!",message,code);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void showDialog (String title, String message, String code){

        builder.setTitle(title);
        if (code.equals("create_true")){

            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    activity.finish();

                }
            });


        }else if (code.equals("create_false")){

            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();


                }
            });

        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
