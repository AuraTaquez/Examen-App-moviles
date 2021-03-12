package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.WebService;
import WebService.Asynchtask;

public class Volumen extends AppCompatActivity implements Asynchtask {
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
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ListaVolumenes= new ArrayList<>();

    }
}