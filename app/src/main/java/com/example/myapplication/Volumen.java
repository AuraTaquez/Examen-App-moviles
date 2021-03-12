package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WebService.WebService;
import WebService.Asynchtask;

public class Volumen extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener  {
    String id;
    ArrayList<Volumenes> ListaVolumenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);
        id = getIntent().getExtras().getString("JID");
        String urlV="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+id;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService(urlV, datos, Volumen.this, Volumen.this);
        ws.execute("");
        ListView list = (ListView) findViewById(R.id.lisvolumen);
        list.setOnItemClickListener(this);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ListaVolumenes= new ArrayList<>();
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i <jsonArray.length(); i++) {
            JSONObject volumenes = jsonArray.getJSONObject(i);
            Volumenes volum = new Volumenes();
            volum.setIssue_id(volumenes.getString("issue_id"));
            volum.setVolume(volumenes.getString("volume"));
            volum.setNumber(volumenes.getString("number"));
            volum.setYear(volumenes.getString("year"));
            volum.setDate_published(volumenes.getString("date_published"));
            volum.setTitle(volumenes.getString("title"));
            volum.setDoi(volumenes.getString("doi"));
            volum.setCover(volumenes.getString("cover"));
            ListaVolumenes.add(volum);
        }
        AdaptadorVolumen adapterVol = new AdaptadorVolumen(this, ListaVolumenes);
        ListView Hview = findViewById(R.id.lisvolumen);
        Hview.setAdapter(adapterVol);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent miIntent = new Intent(Volumen.this, ActivityPublicacion.class);
        miIntent.putExtra("JID", ((Volumenes)adapterView.getItemAtPosition(position)).getIssue_id());
        startActivity(miIntent);
    }
}