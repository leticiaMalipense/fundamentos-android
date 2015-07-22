package com.example.administrador.projeto1.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
        listViewClients.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, Arrays.asList("Nome 1", "Nome 2", "Nome 3")));
        */
        List<Client> listaClient = getClienteList();
        ListView lista = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter adapter = new ClientListAdapter(MainActivity.this, listaClient);
        lista.setAdapter(adapter);
    }

    private List<Client> getClienteList() {
        List<Client> listaClient = new ArrayList<Client>();
        Client c1 = new Client();
        c1.setName("Nome 1");
        c1.setAge(10);

        Client c2 = new Client();
        c2.setName("Nome 2");
        c2.setAge(10);

        listaClient.add(c1);
        listaClient.add(c2);
        return listaClient;
    }
}
