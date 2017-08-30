package com.tah.touchaheart.app;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tah.touchaheart.R;

public class ClothesActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> adapter;
    Spinner spinnerType, spinnerCondition,spinnerGender,spinnerQuantity,spinnerSize,spinnerLocation;
    private EditText Email, Description;
    Button clothes_submit_Btn;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        Email = (EditText) findViewById(R.id.editremail);
        Email.setText(MainTabbedActivity.editEmail);

        Description = (EditText)findViewById(R.id.desctext);
        clothes_submit_Btn =(Button)findViewById(R.id.clothes_button);


        spinnerType = (Spinner) findViewById(R.id.spinnertype);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_Type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnerSize = (Spinner) findViewById(R.id.spinnersize);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_size,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerQuantity = (Spinner) findViewById(R.id.spinnerquantity);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_quantity,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(adapter);
        spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               // Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerLocation = (Spinner) findViewById(R.id.spinnerlocation);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_location,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapter);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               // Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerGender = (Spinner) findViewById(R.id.spinnergender);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              //  Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCondition = (Spinner) findViewById(R.id.spinnercon);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_condition,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCondition.setAdapter(adapter);
        spinnerCondition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               // Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        clothes_submit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.getText().toString().equals("")||Description.getText().toString().equals("")|| spinnerType.getSelectedItem().toString().equals("")|| spinnerGender.getSelectedItem().toString().equals("")|| spinnerQuantity.getSelectedItem().toString().equals("")|| spinnerSize.getSelectedItem().toString().equals("")|| spinnerCondition.getSelectedItem().toString().equals("")|| spinnerLocation.getSelectedItem().toString().equals("")){

                    builder = new AlertDialog.Builder(ClothesActivity.this);
                    builder.setTitle("Something went wrong!!");
                    builder.setMessage("Please fill in all the fields....");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else
                {
                    BackgroundTaskCreate backgroundTaskCreate = new BackgroundTaskCreate(ClothesActivity.this);
                    backgroundTaskCreate.execute("clothessubmit",Email.getText().toString(),Description.getText().toString(),spinnerType.getSelectedItem().toString(),spinnerGender.getSelectedItem().toString(),spinnerQuantity.getSelectedItem().toString(),spinnerSize.getSelectedItem().toString(),spinnerCondition.getSelectedItem().toString(),spinnerLocation.getSelectedItem().toString());
                }


            }
        });













    }
}
