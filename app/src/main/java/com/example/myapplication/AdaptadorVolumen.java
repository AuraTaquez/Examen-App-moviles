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

public class AdaptadorVolumen extends ArrayAdapter<Volumenes> {
    public AdaptadorVolumen(Context context, List<Volumenes> datos) {
        super(context, R.layout.itemvolumen, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.itemvolumen, null);
        TextView titulo = (TextView)item.findViewById(R.id.title);
        titulo.setText(getItem(position).getTitle());
        TextView volumen = item.findViewById(R.id.volumen);
        volumen.setText("Volumen: "+getItem(position).getVolume());
        TextView  numero = item.findViewById(R.id.number);
        numero.setText("Numero: "+getItem(position).getNumber());
        TextView year = item.findViewById(R.id.year);
        year.setText("Año: "+getItem(position).getYear());
        TextView doi = item.findViewById(R.id.doi);
        doi.setText(getItem(position).getDoi());
        ImageView imageView = (ImageView)item.findViewById(R.id.imgVolumen);
        Glide.with(this.getContext())
                .load(getItem(position).getCover())
                //.error(R.drawable.imgnotfound)
                .into(imageView);
        return(item);
    }
}
