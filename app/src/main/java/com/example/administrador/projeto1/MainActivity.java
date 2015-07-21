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
        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);
        listViewClients.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, Arrays.asList("Nome 1", "Nome 2", "Nome 3")));
        */
        List<Cliente> listaCliente = getClienteList();
        ListView lista = (ListView) findViewById(R.id.listViewClients);
        ClientListAdapter adapter = new ClientListAdapter(MainActivity.this, listaCliente);
        lista.setAdapter(adapter);
    }

    private List<Cliente> getClienteList() {
        List<Cliente> listaCliente = new ArrayList<Cliente>();
        Cliente c1 = new Cliente();
        c1.setName("Nome 1");
        c1.setAge(10);

        Cliente c2 = new Cliente();
        c2.setName("Nome 2");
        c2.setAge(10);

        listaCliente.add(c1);
        listaCliente.add(c2);
        return listaCliente;
    }
}
