package com.example.toshiba_pc.real3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by toshiba-pc on 22/12/17.
 */

public class Adapter extends BaseAdapter {
    ArrayList<AnnouncementsDAO> itemdetailsList;
    Context context;
    AnnouncementsDAO d=new AnnouncementsDAO(context);




    public Adapter(Context context,ArrayList<AnnouncementsDAO> list) {

        this.context = context;
        itemdetailsList = list;
    }
        @Override
    public int getCount() {
            return itemdetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemdetailsList.get(position);    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AnnouncementsDAO itemdetail = itemdetailsList.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.customizeditem,
                    null);
        }

        TextView txtStadtitle =convertView.findViewById(R.id.title);
        txtStadtitle.setText(itemdetail.getTitle());

        TextView txtStadprice =convertView.findViewById(R.id.price);
        txtStadprice.setText(itemdetail.getPrice());

         int imageid = context.getResources().getIdentifier("brazil_flag",
                "drawable", context.getPackageName());
        ImageView imageview=convertView.findViewById(R.id.imageView);
        switch (d.getImage()){
            case 1:   imageview.setImageResource(R.drawable.yt4);
            case 2:   imageview.setImageResource(R.drawable.oi6);
            case 3:   imageview.setImageResource(R.drawable.ab2);
            case 4:   imageview.setImageResource(R.drawable.aa8);
        }
        imageview.setImageResource(imageid);
        RelativeLayout relativeLayout=(RelativeLayout)convertView.findViewById(R.id.mylayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Announcementdetails.class);
                intent.putExtra(("id"),itemdetail.getId());
                context.startActivity(intent);
            }

        });

        return convertView;

    }
}
