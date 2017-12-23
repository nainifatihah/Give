package com.ainifathiha.give;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemLists extends ArrayAdapter<Items> {
    private Activity context;
    private int resource;
    List<Items> items;

    public ItemLists(Activity context, int resource, List<Items> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
        TextView textViewDescription = (TextView) v.findViewById(R.id.textViewDescription);
        ImageView img = (ImageView) v.findViewById(R.id.imageViewItem);

        textViewName.setText(items.get(position).getName());
        textViewDescription.setText(items.get(position).getDescription());
        Glide.with(context).load(items.get(position).getUrl()).into(img);


        return v;
    }
}
