package com.example.myapplication;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorRevista extends ArrayAdapter<Revista> {
    public AdaptadorRevista(Context context, List<Revista> datos) {
        super(context, R.layout.activity_item, datos);

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.activity_item, null);
        TextView lblTitulo = (TextView)item.findViewById(R.id.texnombre);
        lblTitulo.setText(getItem(position).getName());
        TextView lblDescripcion = item.findViewById(R.id.description);
        lblDescripcion.setText(Html.fromHtml(getItem(position).getDescription()));
        ImageView imageView = (ImageView)item.findViewById(R.id.imgRevista);
        Glide.with(this.getContext())
                .load(getItem(position).getPortada())
                //.error(R.drawable.imgnotfound)
                .into(imageView);
        return(item);
    }


}
