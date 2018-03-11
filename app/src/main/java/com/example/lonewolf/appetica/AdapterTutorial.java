package com.example.lonewolf.appetica;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jj_lo on 08/03/2018.
 */

public class AdapterTutorial extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<TutorialList> tutorial_list;

    public AdapterTutorial(Activity activity, ArrayList<TutorialList> tutorial_list){
        this.activity = activity;
        this.tutorial_list = tutorial_list;
    }

    @Override
    public int getCount() {
        return tutorial_list.size();
    }

    @Override
    public Object getItem(int i) {
        return tutorial_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(view == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_category, null);
        }
        TutorialList tutorial = tutorial_list.get(i);
        TextView text = (TextView) v.findViewById(R.id.tutorial_title);
        text.setText(tutorial.getTitle());
        ImageView image = (ImageView) v.findViewById(R.id.tutorial_logo);
        image.setImageDrawable(tutorial.getImage());
        return v;
    }
}
