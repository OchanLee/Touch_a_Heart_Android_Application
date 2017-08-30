package com.tah.touchaheart.app.retrieve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tah.touchaheart.R;


public class DetailActivity extends AppCompatActivity {
    TextView typetext, descriptiontext, emailtext, quantityText,locationtext,sizetext,conditiontext,gendertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        typetext=(TextView) findViewById(R.id.typetext);
        descriptiontext=(TextView) findViewById(R.id.descriptionfull);
        emailtext = (TextView) findViewById(R.id.emailtext);
        locationtext = (TextView) findViewById(R.id.locationtext);
        gendertext = (TextView) findViewById(R.id.gendertext);
        sizetext = (TextView) findViewById(R.id.sizetext);
        conditiontext = (TextView) findViewById(R.id.conditiontext);
        quantityText = (TextView) findViewById(R.id.quantitytext);


        Intent i =this.getIntent();
        String email =i.getExtras().getString("EMAIL_KEY");
        String type =i.getExtras().getString("TYPE_KEY");
        String description =i.getExtras().getString("DESCRIPTION_KEY");
        String rgender =i.getExtras().getString("GENDER_KEY");
        String rlocation =i.getExtras().getString("LOCATION_KEY");
        String rsize =i.getExtras().getString("SIZE_KEY");
        String rquantity =i.getExtras().getString("QUANTITY_KEY");
        String rcondition =i.getExtras().getString("CONDITION_KEY");


        emailtext.setText(email);
        descriptiontext.setText(description);
        typetext.setText(type);
        locationtext.setText(rlocation);
        gendertext.setText(rgender);
        sizetext.setText(rsize);
        quantityText.setText(rquantity);
        conditiontext.setText(rcondition);



    }
}
