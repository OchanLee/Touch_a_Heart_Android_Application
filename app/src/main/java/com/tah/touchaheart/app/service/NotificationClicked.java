package com.tah.touchaheart.app.service;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.tah.touchaheart.R;
import com.tah.touchaheart.app.database.MyDBHandler;

;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tah.touchaheart.R.drawable.img;

public class NotificationClicked extends AppCompatActivity {


    String date;
    TextView textHead, textContent;
    ImageView cameraImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_clicked);

        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        textHead = (TextView) findViewById(R.id.textnotificationHeading);
        textContent = (TextView) findViewById(R.id.textnotification_details);
        cameraImage = (ImageView) findViewById(R.id.cameraImage);

       // view_Needs = (Button) findViewById(R.id.viewcreatedNeeds);

        textHead.setText("HELLO " + MyService.r_email + " YOUR NEED HAS BEEN FINALLY MET!");
        Picasso.with(this).load(MyService.image_path).fit().centerCrop().into(cameraImage);





        if (MyService.quantity <= 1)
            textContent.setText(MyService.email + " donated " + MyService.quantity + " " + MyService.condition + " " + MyService.type + " of size " + MyService.size + " to satisfy your need. Please hurry to our " + MyService.location + " Office and pick what belongs to you.");
        else
            textContent.setText(MyService.email + " donated " + MyService.quantity + " " + MyService.condition + " " + MyService.type + "s" + " of size " + MyService.size + " to satisfy your need. Please hurry to our " + MyService.location + " Office and pick what belongs to you.");



    }



}
