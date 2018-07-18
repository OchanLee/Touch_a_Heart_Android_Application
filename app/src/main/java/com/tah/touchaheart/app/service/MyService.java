package com.tah.touchaheart.app.service;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.tah.touchaheart.R;
import com.tah.touchaheart.app.MainTabbedActivity;
import com.tah.touchaheart.app.database.MyDBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.widget.Toast.*;

/**
 * Created by Lee on 8/24/2017.
 */


public class MyService extends JobService {
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    String json_string;
    public static String email, type, gender, size, condition, location, image_path, r_email, dbSting, r_ID, d_ID;
    public static int quantity, r_quantity;
    MyDBHandler dbHandler;


    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        dbHandler = new MyDBHandler(this, null, null, 1);
        dbSting = dbHandler.databasetostring();


        new BackgroundTask(this).execute();
        //makeText(this, "onStartJob", LENGTH_SHORT).show();


        jobFinished(jobParameters, false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    AsyncTask myAsyncTask = new BackgroundTask(this);

    private class BackgroundTask extends AsyncTask<Void, Void, String> {


        String json_url = "http://" + MainTabbedActivity.url_address + "/touchaheartapp/notification.php";
        private MyService myService;


        @Override
        protected String doInBackground(Void... voids) {


            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((json_string = bufferedReader.readLine()) != null) {

                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        public BackgroundTask(MyService myService) {
            this.myService = myService;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            if (result == null) {

            } else {

                try {

                    JSONObject jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("server_response");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject JO = jsonArray.getJSONObject(i);
                        d_ID = JO.getString("d_id");
                        email = JO.getString("d_email");
                        type = JO.getString("d_type");
                        gender = JO.getString("d_gender");
                        quantity = JO.getInt("d_quantity");
                        size = JO.getString("d_size");
                        condition = JO.getString("d_condition");
                        location = JO.getString("d_location");
                        image_path = JO.getString("image_path");
                        r_ID = JO.getString("r_id");
                        r_email = JO.getString("remail");
                        r_quantity = JO.getInt("rquantity");

                        if (dbSting.toString().trim().equals(r_email)) {
                            // Toast.makeText(myService, r_email, Toast.LENGTH_LONG).show();
                            break;
                        }


                    }


                    if (dbSting.toString().trim().equals(r_email)) {
                        notification = new NotificationCompat.Builder(myService);


                        // define sound URI, the sound to be played when there's a notification
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                        notification.setAutoCancel(true);
                        notification.setSmallIcon(R.drawable.tahnoticon);
                        notification.setTicker("A donation has been made");
                        notification.setWhen(System.currentTimeMillis());
                        notification.setContentTitle("Need has been met!");
                        notification.setContentText("Touch to view message");
                        notification.setSound(soundUri);


                        Intent intent = new Intent(myService, NotificationClicked.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(myService, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        notification.setContentIntent(pendingIntent);

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(uniqueID, notification.build());

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }


    }

}
