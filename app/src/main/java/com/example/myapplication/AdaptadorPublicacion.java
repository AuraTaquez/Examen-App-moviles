package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorPublicacion extends ArrayAdapter<publicacion> {
    public AdaptadorPublicacion(Context context, List<publicacion> datos) {
        super(context, R.layout.itempublicacion, datos);
    }

    String url;
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.itempublicacion, null);
        TextView titulo = (TextView)item.findViewById(R.id.pubTitulo);
        titulo.setText(getItem(position).getTitle());
        TextView volumen = item.findViewById(R.id.Abstrct);
        volumen.setText(Html.fromHtml(getItem(position).getAbstractP()));
        url = getItem(position).getUrlViewGalleyPdf();
        Button PDf = item.findViewById(R.id.pdf);
        PDf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llama(url);
            }
        });
        return(item);
    }
    public void llama(String url){
        new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

}
