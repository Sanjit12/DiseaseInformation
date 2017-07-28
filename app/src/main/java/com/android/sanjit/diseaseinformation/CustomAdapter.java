package com.android.sanjit.diseaseinformation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{
    CustomAdapter(Context context,String[] numbers){
        super(context,R.layout.custom_row,numbers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater si = LayoutInflater.from(getContext());
        View customView = si.inflate(R.layout.custom_row,parent,false);

        String[] number = getItem(position).split("-");
        TextView name = (TextView) customView.findViewById(R.id.textView8);
        TextView Pnumber = (TextView) customView.findViewById(R.id.textView9);

        name.setText(number[0]);
        Pnumber.setText(number[1]);
        return customView;
    }

}
