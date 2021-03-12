package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONException;

import java.util.HashMap;
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
        



    }
}