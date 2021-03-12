package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class ActivityPublicacion extends AppCompatActivity implements Asynchtask {
    String id;
    String seccion;
    ArrayList<publicacion> ListaPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);
        id = getIntent().getExtras().getString("JID");
        String urlPub = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + id;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService(urlPub, datos, ActivityPublicacion.this, Volumen.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ListaPublicacion = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject publication = jsonArray.getJSONObject(i);
            publicacion publi = new publicacion();
            publi.setSection(publication.getString("section"));
            publi.setPublication_id(publication.getString("publication_id"));
            publi.setTitle(publication.getString("title"));
            publi.setDoi(publication.getString("doi"));
            publi.setAbstractP(publication.getString("abstract"));
            publi.setDate_published(publication.getString("date_published"));
            JSONArray galeysPdf = publication.getJSONArray("galeys");
            JSONObject galeysPdfobjpdf = galeysPdf.getJSONObject(0);
            publi.setUrlViewGalleyPdf(galeysPdfobjpdf.getString("UrlViewGalley"));
            JSONObject galeysPdfobjhtml = galeysPdf.getJSONObject(1);
            publi.setUrlViewGalleyHtml(galeysPdfobjhtml.getString("UrlViewGalley"));
            ListaPublicacion.add(publi);
        }

    }
}