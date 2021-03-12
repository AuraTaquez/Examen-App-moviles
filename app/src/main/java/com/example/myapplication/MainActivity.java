package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import WebService.WebService;
import WebService.Asynchtask;

public  class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://revistas.uteq.edu.ec/ws/journals.php", datos, MainActivity.this, MainActivity.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Revista> ListaRevista = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(result);
        JSONObject jresults = jsonObject.getJSONObject("Results");
        Iterator<?> iterator = jresults.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            JSONObject Revistas = jresults.getJSONObject(key);
            Revista revista = new Revista();
            revista.setJournal_id(Revistas.getString("journal_id"));
            revista.setPortada(Revistas.getString(("portada")));
            revista.setAbbreviation(Revistas.getString("abbreviation"));
            revista.setDescription(Revistas.getString("description"));
            revista.setJournalThumbnail(Revistas.getString("journalThumbnail"));
            revista.setName(Revistas.getString("name"));
        }
    }
}