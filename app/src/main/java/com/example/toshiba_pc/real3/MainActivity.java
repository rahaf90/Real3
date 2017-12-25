package com.example.toshiba_pc.real3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AnnouncementsDAO d=new AnnouncementsDAO(this);
    Adapter adapter;
    ListView mlistview;
    ApartementDAO a;
    LandDAO l;
    HouseDAO h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n3=new Intent(MainActivity.this,MessagesList.class);
                n3.setAction(Intent.ACTION_VIEW);
                startActivity(n3);
            }
        });

        mlistview=findViewById(R.id.mylist);
        h.addhouse("there is elevator","no garden","no swimming pool","heaven street");
        d.addannouncement(1,"house", String.valueOf(40000));
        d.addannouncement(2,"vella", String.valueOf(509000));
        d.addannouncement(3,"castle", String.valueOf(50000000));
        populateLitsFromDB();
    }
    private void populateLitsFromDB() {
        ArrayList<AnnouncementsDAO>daos=d.getAllannouncement(this);
        adapter=new Adapter(this,daos);
        mlistview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.home):
                return true;
            case (R.id.send):
                Intent n = new Intent(MainActivity.this,MessagesList.class);
                n.setAction(Intent.ACTION_VIEW);
                startActivity(n);


        }
        return super.onOptionsItemSelected(item);
    }

}

