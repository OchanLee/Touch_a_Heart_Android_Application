package com.tah.touchaheart.app.service;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public  class  UpdateMethods {

    public static Context context;

    public UpdateMethods() {

    }

    public UpdateMethods(Context c) {
        context = c;

    }
    public void updateDatabaseNotif()
    {


        //Below is the original code for on notififcation clicked

        if (MyService.quantity == MyService.r_quantity) {

            BackgroundTaskUpdate backgroundTaskUpdate = new BackgroundTaskUpdate(UpdateMethods.context);
            backgroundTaskUpdate.execute("finished", MyService.d_ID, MyService.r_ID);

        } else if (MyService.quantity < MyService.r_quantity) {

            String new_r_quantity = String.valueOf(MyService.r_quantity - MyService.quantity);

            BackgroundTaskUpdate backgroundTaskUpdate = new BackgroundTaskUpdate(UpdateMethods.context);
            backgroundTaskUpdate.execute("D_quantity_less", MyService.d_ID, MyService.r_ID,new_r_quantity);

        } else {
            String new_d_quantity = String.valueOf(MyService.quantity - MyService.r_quantity);

            BackgroundTaskUpdate backgroundTaskUpdate = new BackgroundTaskUpdate(UpdateMethods.context);
            backgroundTaskUpdate.execute("D_quantity_more", MyService.d_ID, MyService.r_ID,new_d_quantity);

        }


        //code for notification clicked ends here




        //String a = "Value Called from another class";
       // return a;

    }

}
