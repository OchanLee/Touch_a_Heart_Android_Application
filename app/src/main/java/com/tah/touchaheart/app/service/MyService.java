package com.tah.touchaheart.app.service;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.tah.touchaheart.R;
import com.tah.touchaheart.app.ClothesActivity;
import com.tah.touchaheart.app.LoginActivity;
import com.tah.touchaheart.app.MainTabbedActivity;
import com.tah.touchaheart.app.RegisterActivity;
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
    private static final int uniqueID = 45;
    String json_string;
    public static String email, type, gender, size, condition, location, image_path, r_email, dbSting, r_ID, d_ID, CHANNEL_ID;
    public static int quantity, r_quantity;
    MyDBHandler dbHandler;



    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        dbHandler = new MyDBHandler(this, null, null, 1);
        dbSting = dbHandler.databasetostring();


        new BackgroundTaskService(this).execute();
        //makeText(this, "onStartJob", LENGTH_SHORT).show();



        jobFinished(jobParameters, false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    AsyncTask myAsyncTask = new BackgroundTaskService(this);

    private class BackgroundTaskService extends AsyncTask<Void, Void, String> {


        String json_url = "http://" + MainTabbedActivity.url_address + "/touchaheartapp/notification.php";
        MyService myService;


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

        public BackgroundTaskService(MyService myService) {
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

                //Toast.makeText(myService, "no jason data here", Toast.LENGTH_LONG).show();

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


                        //old notification


                        // define sound URI, the sound to be played when there's a notification
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        Intent intent = new Intent(MyService.this, NotificationClicked.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                        notification = new NotificationCompat.Builder(MyService.this);

                        notification.setAutoCancel(true);
                        notification.setSmallIcon(R.drawable.tahnoticon);
                        notification.setTicker("A donation has been made");
                        notification.setWhen(System.currentTimeMillis());
                        notification.setContentTitle("Received a Donation!");
                        notification.setContentText("Touch to view message");
                        notification.setSound(soundUri);
                        notification.setContentIntent(pendingIntent);

                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(uniqueID, notification.build());

                        UpdateMethods updateMethods = new UpdateMethods(UpdateMethods.context);
                        updateMethods.updateDatabaseNotif();

                        //end of old notification
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }




                //String b = updateMethods.updateDatabaseNotif();

               // Toast.makeText(myService,b,Toast.LENGTH_LONG).show();


            }


        }


    }

}
