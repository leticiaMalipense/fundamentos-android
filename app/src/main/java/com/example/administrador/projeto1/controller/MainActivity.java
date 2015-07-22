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


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView lista;

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
        lista = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter adapter = new ClientListAdapter(MainActivity.this, listaClient);
        lista.setAdapter(adapter);
    }

    private List<Client> getClienteList() {
        return MemoryClientRepository.getInstance().getAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent intent = new Intent(MainActivity.this, SaveClientActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClientListAdapter adapter = (ClientListAdapter) lista.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }
}
