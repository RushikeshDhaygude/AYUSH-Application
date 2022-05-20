package com.example.ayush1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    public MainAdapter(HospitalList hospitalList, ArrayList<String> str) {
        this.hospitalList = hospitalList;
        this.str = str;
    }

    HospitalList hospitalList;
    ArrayList<String> str;
    Animation animation;


    @Override
    public int getCount() {
        return str.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(hospitalList).inflate(R.layout.layoutitem,parent,false);
        animation = AnimationUtils.loadAnimation(hospitalList, R.anim.animation1);
        TextView textView;
        LinearLayout linearLayout;
        linearLayout = convertView.findViewById(R.id.linear);
        textView = convertView.findViewById(R.id.textview);

        textView.setText(str.get(position));
        textView.setAnimation(animation);

        return convertView;
    }
}
