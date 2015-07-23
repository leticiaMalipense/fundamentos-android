package com.example.administrador.projeto1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;

import java.util.List;


public class ClientListActivity extends AppCompatActivity {

    private static final String TAG = ClientListActivity.class.getSimpleName();
    private ListView listClient;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindClientList();
    }

    private void bindClientList() {
    /*
    ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
    listViewClients.setAdapter(new ArrayAdapter<String>(ClientListActivity.this,
            android.R.layout.simple_list_item_1, Arrays.asList("Nome 1", "Nome 2", "Nome 3")));
    */
        List<Client> listaClient = getClienteList();
        listClient = (ListView) findViewById(R.id.listViewClients);
        final ClientListAdapter adapter = new ClientListAdapter(ClientListActivity.this, listaClient);
        listClient.setAdapter(adapter);

        listClient.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);
                return false;
            }
        });

        registerForContextMenu(listClient);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuRemove) {
            client.delete();
            refreshClientList();
            Toast.makeText(ClientListActivity.this, R.string.success, Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menuEdit) {
            Intent intent = new Intent(ClientListActivity.this, SaveClientActivity.class);
            intent.putExtra(SaveClientActivity.CLIENT_PARAM, (Parcelable) client);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
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
            Intent intent = new Intent(ClientListActivity.this, SaveClientActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClientList();
    }

    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listClient.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }
}
