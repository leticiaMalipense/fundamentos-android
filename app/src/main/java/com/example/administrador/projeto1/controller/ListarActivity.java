package com.example.administrador.projeto1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Cliente;
import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;

import java.util.Arrays;
import java.util.List;

public class ListarActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar);
        List<Cliente> listaCliente = MemoryClientRepository.getInstance().getAll();
        ListView lista = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter adapter = new ClientListAdapter(ListarActivity.this, listaCliente);
        lista.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_cliente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent intencao = new Intent(ListarActivity.this, CadastroActivity.class);
            startActivity(intencao);
        }
        return super.onOptionsItemSelected(item);
    }
}
