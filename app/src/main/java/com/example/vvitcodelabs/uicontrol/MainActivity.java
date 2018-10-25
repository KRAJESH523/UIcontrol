package com.example.vvitcodelabs.uicontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText contact_name,contact_number,contact_email;
    Spinner contact_type;
    Button b1;
    AlertDialog.Builder builder;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contact_name = findViewById(R.id.id_contact_name);
        contact_type = findViewById(R.id.spinner);
        contact_email = findViewById(R.id.id_contact_email);
        contact_number = findViewById(R.id.id_contact_number);
        b1 = findViewById(R.id.id_contact_save);
        helper = new DBHelper(this,null,null, 0);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("do u want to save the contact");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Contact contact = new Contact();
                        contact.setContact_name(contact_name.getText().toString());
                        contact.setContact_email(contact_email.getText().toString());
                        contact.setContact_number(contact_number.getText().toString());
                        contact.setContact_type(contact_type.getSelectedItem().toString());

                        helper.storeData(contact);

                        Toast.makeText(getBaseContext(),"Contact saved",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

}
