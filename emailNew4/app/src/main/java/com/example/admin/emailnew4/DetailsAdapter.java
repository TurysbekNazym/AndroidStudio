package com.example.admin.emailnew4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Created by admin on 17.09.2017.
 */

public class DetailsAdapter extends ArrayAdapter<Details> {
     private final Context context;
     private final ArrayList<Details> detailArrayList;

    public DetailsAdapter(Context context1, ArrayList<Details> detailArrayList1){

        super(context1,R.layout.listvieweachitem,detailArrayList1);
        this.context = context1;
        this.detailArrayList = detailArrayList1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.listvieweachitem, parent, false);

        ImageView img = (ImageView) rowView.findViewById(R.id.img);
        img.setImageResource(detailArrayList.get(position).getImage_id());

        TextView sender1 = (TextView) rowView.findViewById(R.id.sender);
        sender1.setText(detailArrayList.get(position).getName_sender());

        TextView title1 = (TextView) rowView.findViewById(R.id.titleId);
        title1.setText(detailArrayList.get(position).getSms_title());

        TextView dates = (TextView) rowView.findViewById(R.id.date);
        dates.setText(detailArrayList.get(position).getDate());


        // 5. retrn rowView
        return rowView;
    }
}
