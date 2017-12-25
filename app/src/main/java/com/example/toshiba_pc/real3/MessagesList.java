package com.example.toshiba_pc.real3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MessagesList extends AppCompatActivity {
    ListView listview;
    SQLiteDatabase sqLiteDatabase;
    MessageDAO m;
    Cursor cursor;
    Textadapter textadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listview = findViewById(R.id.listview);
        textadapter=new Textadapter(getApplicationContext(),R.layout.mymessageitem);
        listview.setAdapter(textadapter);
        m = new MessageDAO(getApplicationContext());
        cursor = m.getallAnnouncements(sqLiteDatabase);
        if (cursor.moveToFirst()) {

            do {
                String date,nemail,uemail,phonen,textm;
                date=cursor.getString(1);
                nemail=cursor.getString(2);
                uemail=cursor.getString(3);
                phonen=cursor.getString(4);
                textm=cursor.getString(5);
                m=new MessageDAO(date,nemail,uemail,phonen,textm,this);
                textadapter.add(m);




            }while (cursor.moveToNext());
            }

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
                Intent n = new Intent(MessagesList.this, MainActivity.class);
                n.setAction(Intent.ACTION_VIEW);
                startActivity(n);

            case (R.id.send):
                return true;

        }
        return super.onOptionsItemSelected(item);
}
}


