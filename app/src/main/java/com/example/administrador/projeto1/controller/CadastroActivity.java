package com.example.administrador.projeto1.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Cliente;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtAge;
    private EditText txtAddress;
    private EditText txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_cadastro);

        txtName = (EditText) findViewById(R.id.txtName);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {
            Cliente cliente = bindCliente();
            cliente.save();

            Toast.makeText(CadastroActivity.this, Cliente.getAll().toString() ,Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private Cliente bindCliente() {
        Cliente cliente = new Cliente();
        cliente.setName(txtName.getText().toString());
        cliente.setAge(Integer.valueOf(txtAge.getText().toString()));
        cliente.setEndereco(txtAddress.getText().toString());
        cliente.setTelefone(txtPhone.getText().toString());
        return cliente;
    }

}

