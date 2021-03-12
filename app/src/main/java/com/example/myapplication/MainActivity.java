package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {
    ArrayList<Revista> ListaRevista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://revistas.uteq.edu.ec/ws/journals.php", datos, MainActivity.this, MainActivity.this);
        ws.execute("");
        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        getPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        ListView list = (ListView) findViewById(R.id.ListaHview);
        list.setOnItemClickListener(this);
    }

    public void getPermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED))
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
        }
    }


    @Override
    public void processFinish(String result) throws JSONException {
        ListaRevista = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i <jsonArray.length(); i++) {
            JSONObject Revistas = jsonArray.getJSONObject(i);
            Revista revista = new Revista();
            revista.setJournal_id(Revistas.getString("journal_id"));
            revista.setPortada(Revistas.getString(("portada")));
            revista.setAbbreviation(Revistas.getString("abbreviation"));
            revista.setDescription(Revistas.getString("description"));
            revista.setJournalThumbnail(Revistas.getString("journalThumbnail"));
            revista.setName(Revistas.getString("name"));
            ListaRevista.add(revista);
        }
        AdaptadorRevista adapter = new AdaptadorRevista(this, ListaRevista);
        ListView Hview = findViewById(R.id.ListaHview);
        Hview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent miIntent = new Intent(MainActivity.this, Volumen.class);
        miIntent.putExtra("JID", ((Revista) adapterView.getItemAtPosition(position)).getJournal_id());
        startActivity(miIntent);
    }
}