package com.tah.touchaheart.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tah.touchaheart.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClothesDonateActivity extends AppCompatActivity {

    private Button button,donateButton;
    private String encoded_string, image_name;
    private Bitmap bitmap;
    private ImageView imageViewClothes;
    private File file;
    private Uri file_uri;
    private EditText DEmail, REmail;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;
    Context ctx;


    ArrayAdapter<CharSequence> adapter;
    private Spinner spinnerType, spinnerCondition,spinnerGender,spinnerQuantity,spinnerSize,spinnerLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_donate);

        DEmail = (EditText) findViewById(R.id.editdonatoremaildonate);
        REmail = (EditText) findViewById(R.id.editreceipientmaildonate);
        DEmail.setText(MainTabbedActivity.editEmail);
        REmail.setText("Any");



        button = (Button) findViewById(R.id.capture_button_donate);
        imageViewClothes = (ImageView) findViewById(R.id.imageclothesdonate);
        donateButton = (Button) findViewById(R.id.clothes_button_donate);



        spinnerType = (Spinner) findViewById(R.id.spinnertypedonate);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_Type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               // Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnerSize = (Spinner) findViewById(R.id.spinnersizedonate);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_size,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSize.setAdapter(adapter);
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              //  Toast.makeText(getBaseContext(),adapterView.getSelectedItem().toString() +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerQuantity = (Spinner) findViewById(R.id.spinnerquantitydonate);
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

        spinnerLocation = (Spinner) findViewById(R.id.spinnerlocationdonate);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_location_donate,android.R.layout.simple_spinner_item);
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

        spinnerGender = (Spinner) findViewById(R.id.spinnergenderdonate);
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

        spinnerCondition = (Spinner) findViewById(R.id.spinnercondonate);
        adapter = ArrayAdapter.createFromResource(this,R.array.clothes_condition_donate,android.R.layout.simple_spinner_item);
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




        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DEmail.getText().toString().equals("")||REmail.getText().toString().equals("")|| spinnerType.getSelectedItem().toString().equals("")|| spinnerGender.getSelectedItem().toString().equals("")|| spinnerQuantity.getSelectedItem().toString().equals("")|| spinnerSize.getSelectedItem().toString().equals("")|| spinnerCondition.getSelectedItem().toString().equals("")|| spinnerLocation.getSelectedItem().toString().equals("")){

                    builder = new AlertDialog.Builder(ClothesDonateActivity.this);
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
                } else
                {
                    BackgroundTaskDonate backgroundTaskDonate = new BackgroundTaskDonate(ClothesDonateActivity.this);
                    backgroundTaskDonate.execute("clothesdonate",REmail.getText().toString(),DEmail.getText().toString(),spinnerType.getSelectedItem().toString(),spinnerGender.getSelectedItem().toString(),spinnerQuantity.getSelectedItem().toString(),spinnerSize.getSelectedItem().toString(),spinnerCondition.getSelectedItem().toString(),spinnerLocation.getSelectedItem().toString());
                }





            }
        });









        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(i,10);
            }
        });
    }

    private void getFileUri() {

        image_name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        image_name = image_name + ".jpg";
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + image_name
        );

        file_uri = Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK){

            bitmap = BitmapFactory.decodeFile(file_uri.getPath());
            imageViewClothes.setImageBitmap(bitmap);
            new Encode_image().execute();
        }
    }

    private class Encode_image extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... voids) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array,0);
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            donateButton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    if (DEmail.getText().toString().equals("")||REmail.getText().toString().equals("")|| spinnerType.getSelectedItem().toString().equals("")|| spinnerGender.getSelectedItem().toString().equals("")|| spinnerQuantity.getSelectedItem().toString().equals("")|| spinnerSize.getSelectedItem().toString().equals("")|| spinnerCondition.getSelectedItem().toString().equals("")|| spinnerLocation.getSelectedItem().toString().equals("")){

                        builder = new AlertDialog.Builder(ClothesDonateActivity.this);
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
                        makeRequest();

                    }




                }
            });

        }
    }

    private void makeRequest() {


        RequestQueue requestQue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,"http://192.168.42.57/touchaheartapp/donate.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("encoded_string", encoded_string);
                map.put("image_name", image_name);
                map.put("d_email", DEmail.getText().toString());
                map.put("r_email", REmail.getText().toString());
                map.put("d_type", spinnerType.getSelectedItem().toString());
                map.put("d_gender", spinnerGender.getSelectedItem().toString());
                map.put("d_quantity", spinnerQuantity.getSelectedItem().toString());
                map.put("d_size", spinnerSize.getSelectedItem().toString());
                map.put("d_condition", spinnerCondition.getSelectedItem().toString());
                map.put("d_location", spinnerLocation.getSelectedItem().toString());

                return map;
            }
        };
        requestQue.add(request);
    }
}
