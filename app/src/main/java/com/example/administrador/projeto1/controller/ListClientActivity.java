package com.example.administrador.projeto1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;

import java.util.List;

public class ListClientActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        List<Client> listaClient = MemoryClientRepository.getInstance().getAll();
        ListView lista = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter adapter = new ClientListAdapter(ListClientActivity.this, listaClient);
        lista.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list_client, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent intencao = new Intent(ListClientActivity.this, SaveClientActivity.class);
            startActivity(intencao);
        }
        return super.onOptionsItemSelected(item);
    }
}
