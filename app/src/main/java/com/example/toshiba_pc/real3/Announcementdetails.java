package com.example.toshiba_pc.real3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Announcementdetails extends AppCompatActivity {

    AnnouncementsDAO d=new AnnouncementsDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcementdetails);
        int id =getIntent().getIntExtra("id", 0);
        d.getannouncement(id,this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n3=new Intent(Announcementdetails.this,MessagesList.class);
                n3.setAction(Intent.ACTION_VIEW);
                startActivity(n3);
            }
        });
    }


    public void contact(View view){

        Intent n4=new Intent(Announcementdetails.this,ContactAnnouncer.class);
        n4.setAction(Intent.ACTION_VIEW);
        startActivity(n4);



    }

}
