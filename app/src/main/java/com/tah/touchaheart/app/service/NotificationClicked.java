package com.tah.touchaheart.app.service;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;

import android.widget.TextView;


import com.tah.touchaheart.R;
import com.tah.touchaheart.app.database.MyDBHandler;

;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationClicked extends AppCompatActivity {


    String date;
    TextView textHead, textContent;
    Button view_Needs;

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_clicked);

        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        textHead = (TextView) findViewById(R.id.textnotificationHeading);
        textContent = (TextView) findViewById(R.id.textnotification_details);

        view_Needs = (Button) findViewById(R.id.viewcreatedNeeds);

        textHead.setText("HELLO " + MyService.r_email + " YOUR NEED HAS BEEN FINALLY MET!");


        if (MyService.quantity <= 1)
            textContent.setText(MyService.email + " donated " + MyService.quantity + " " + MyService.condition + " " + MyService.type + " of size " + MyService.size + " to satisfy your need. Please hurry to our " + MyService.location + " Office and pick what belongs to you.");
        else
            textContent.setText(MyService.email + " donated " + MyService.quantity + " " + MyService.condition + " " + MyService.type + "s" + " of size " + MyService.size + " to satisfy your need. Please hurry to our " + MyService.location + " Office and pick what belongs to you.");


        if (MyService.quantity == MyService.r_quantity) {

            BackgroundTaskUpdate backgroundTaskUpdate = new BackgroundTaskUpdate(NotificationClicked.this);
            backgroundTaskUpdate.execute("finished", MyService.d_ID, MyService.r_ID);

        } else if (MyService.quantity < MyService.r_quantity) {

            String new_r_quantity = String.valueOf(MyService.r_quantity - MyService.quantity);

            BackgroundTaskUpdate backgroundTaskUpdate = new BackgroundTaskUpdate(NotificationClicked.this);
            backgroundTaskUpdate.execute("D_quantity_less", MyService.d_ID, MyService.r_ID,new_r_quantity);

        } else {
            String new_d_quantity = String.valueOf(MyService.quantity - MyService.r_quantity);

            BackgroundTaskUpdate backgroundTaskUpdate = new BackgroundTaskUpdate(NotificationClicked.this);
            backgroundTaskUpdate.execute("D_quantity_more", MyService.d_ID, MyService.r_ID,new_d_quantity);

        }


        //  dbHandler = new MyDBHandler(this, null, null, 1);

        // printDatabase();

    }

   /* public void printDatabase() {

        String dbSting = dbHandler.databasetostring();

        textShow.setText(dbSting);

        editdata.setText("");
        editdataTime.setText("");

    }

    public void addButtonClick(View view)

    {

        Products products = new Products(editdataTime.getText().toString());

        dbHandler.addProduct(products);

        printDatabase();

    }

    public void drop(View view)

    {
        dbHandler.refreshTable();
        printDatabase();


    }

    public void deleteButtonClick(View view)

    {

        String inputText = editdata.getText().toString();

        dbHandler.deleteProduct(inputText);

        printDatabase();

    }*/


}
