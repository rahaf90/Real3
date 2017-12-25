package com.example.toshiba_pc.real3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toshiba-pc on 23/12/17.
 */

public class Textadapter extends ArrayAdapter {

List list=new ArrayList();
    public Textadapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class layouthandler{

        TextView date,announceremail,useremail,phonenumber,text;
    }
    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row=convertView;
        layouthandler layouthandler;
        if(row==null){

            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate((R.layout.mymessageitem),parent,false);
            layouthandler=new layouthandler();
            layouthandler.date=row.findViewById(R.id.dateview);
            layouthandler.announceremail=row.findViewById(R.id.announcerview);
            layouthandler.useremail=row.findViewById(R.id.useremailview);
            layouthandler.phonenumber=row.findViewById(R.id.phonenumberview);
            layouthandler.text=row.findViewById(R.id.messageview);
            row.setTag(layouthandler);
        }

        else {


            layouthandler=(layouthandler)row.getTag();

        }

        MessageDAO d=(MessageDAO) this.getItem(position);
        layouthandler.date.setText(d.getDate());
        layouthandler.announceremail.setText(d.getEmailannouncer());
        layouthandler.useremail.setText(d.getEmailuser());
        layouthandler.phonenumber.setText(d.getPhonenumber());
        layouthandler.text.setText(d.getTextmessage());
        return row;
    }


}
