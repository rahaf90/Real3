package com.example.toshiba_pc.real3;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ContactAnnouncer extends AppCompatActivity {
    EditText date;
    EditText announceremail;
    EditText useremail;
    EditText phonenumer;
    EditText textmessage;
    Context context=this;
    SQLite_handler sqLite_handler;
    MessageDAO d;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_announcer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n3=new Intent(ContactAnnouncer.this,MessagesList.class);
                n3.setAction(Intent.ACTION_VIEW);
                startActivity(n3);

            }
        });

        date=findViewById(R.id.date);
        announceremail=findViewById(R.id.announceremail);
        useremail=findViewById(R.id.useremail);
        phonenumer=findViewById(R.id.phone);
        textmessage=findViewById(R.id.messag);
    }


    public void send(View view){
        String datee=date.getText().toString();
        String announcer=announceremail.getText().toString();
        String user=useremail.getText().toString();
        String phone=phonenumer.getText().toString();
        String message=textmessage.getText().toString();
        d=new MessageDAO(context);
        d.addMessage(datee,announcer,user,phone,message);
        Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case (R.id.home):
                Intent n = new Intent(ContactAnnouncer.this, MainActivity.class);
                n.setAction(Intent.ACTION_VIEW);
                startActivity(n);

            case (R.id.send):
                Intent n1 = new Intent(ContactAnnouncer.this,MessagesList.class);
                n1.setAction(Intent.ACTION_VIEW);
                startActivity(n1);

        }
        return super.onOptionsItemSelected(item);
    }
}
