package com.tah.touchaheart.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tah.touchaheart.R;

public class LoginActivity extends AppCompatActivity {
    EditText Email, Pass;
    Button login_button;
    AlertDialog.Builder builder;

    TextView signup_text,read_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (EditText)findViewById(R.id.email);
        Pass = (EditText)findViewById(R.id.password);
        login_button =(Button)findViewById(R.id.login_button);
        signup_text = (TextView)findViewById(R.id.sign_up);




        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class ));

            }
        });






        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Email.getText().toString().equals("")|| Pass.getText().toString().equals("")){

                    builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Something went wrong!!");
                    builder.setMessage("Please check your inputs....");
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
                    BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
                    backgroundTask.execute("login",Email.getText().toString(),Pass.getText().toString());
                }




            }
        });


    }
}
