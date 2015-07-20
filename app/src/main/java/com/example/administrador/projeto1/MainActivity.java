package com.example.administrador.projeto1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        List<String> clienteName = new ArrayList<String>();
        clienteName.add("Nome 1");
        clienteName.add("Nome 2");
        clienteName.add("Nome 2");

        ListView lista = (ListView) findViewById(R.id.listViewClients);
        lista.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, clienteName.toArray(new String[]{})));
        */

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
        listViewClients.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, Arrays.asList("Nome 1", "Nome 2", "Nome 3")));
    }
}
