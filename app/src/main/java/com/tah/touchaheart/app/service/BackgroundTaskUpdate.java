package com.tah.touchaheart.app.service;

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
import com.tah.touchaheart.app.MainTabbedActivity;

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
 * Created by Lee on 8/29/2017.
 */

public class BackgroundTaskUpdate extends AsyncTask<String,Void,String> {
    String finish_url = "http://"+MainTabbedActivity.url_address+"/touchaheartapp/update.php";
    String d_quantity_less_url = "http://"+MainTabbedActivity.url_address+"/touchaheartapp/d_less.php";
    String d_quantity_more_url = "http://"+MainTabbedActivity.url_address+"/touchaheartapp/d_more.php";
    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    public BackgroundTaskUpdate(Context ctx)
    {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected void onPreExecute() {
       /* builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Please Wait..be Patient");
        progressDialog.setMessage("Connecting to Server");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        */
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];

        if (method.equals("finished"))
        {
            try {
                URL url = new URL(finish_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String d_id = params[1];
                String r_id = params[2];
                String data = URLEncoder.encode("d_id", "UTF-8")+"="+URLEncoder.encode(d_id,"UTF-8")+"&"+
                        URLEncoder.encode("r_id", "UTF-8")+"="+URLEncoder.encode(r_id,"UTF-8")+"&";
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
        else if (method.equals("D_quantity_less"))
        {
            try {
                URL url = new URL(d_quantity_less_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String d_id, r_id,rquantity;
                d_id = params[1];
                r_id = params[2];
                rquantity = params[3];
                String data = URLEncoder.encode("d_id", "UTF-8")+"="+URLEncoder.encode(d_id,"UTF-8")+"&"+
                        URLEncoder.encode("r_id", "UTF-8")+"="+URLEncoder.encode(r_id,"UTF-8")+"&"+
                        URLEncoder.encode("rquantity", "UTF-8")+"="+URLEncoder.encode(rquantity,"UTF-8");
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if (method.equals("D_quantity_more")){

            try {
                URL url = new URL(d_quantity_more_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String d_id, r_id,d_quantity;
                d_id = params[1];
                r_id = params[2];
                d_quantity = params[3];
                String data = URLEncoder.encode("d_id", "UTF-8")+"="+URLEncoder.encode(d_id,"UTF-8")+"&"+
                        URLEncoder.encode("r_id", "UTF-8")+"="+URLEncoder.encode(r_id,"UTF-8")+"&"+
                        URLEncoder.encode("d_quantity", "UTF-8")+"="+URLEncoder.encode(d_quantity,"UTF-8");
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
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

       /* try {
            progressDialog.dismiss();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            if (code.equals("reg_true")){

                showDialog("Registration Success",message,code);

            }else if (code.equals("reg_false")){

                showDialog("Registration Failed",message,code);
            }
            else if (code.equals("login_true"))
            {
                Intent intent = new Intent(activity, MainTabbedActivity.class);
                intent.putExtra("message",message);
                activity.startActivity(intent);
                Toast.makeText(ctx, "Log in Success. ", Toast.LENGTH_LONG).show();



            }
            else if (code.equals("login_false"))
            {
                showDialog("Login Error!...",message,code);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

    }
    public void showDialog (String title, String message, String code){

        builder.setTitle(title);
        if (code.equals("reg_true") || code.equals("reg_false")){

            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    activity.finish();
                }
            });


        }

        else  if (code.equals("login_false"))
        {
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText email, password;
                    email = (EditText) activity.findViewById(R.id.email);
                    password = (EditText) activity.findViewById(R.id.password);
                    email.setText("");
                    password.setText("");
                    dialogInterface.dismiss();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
