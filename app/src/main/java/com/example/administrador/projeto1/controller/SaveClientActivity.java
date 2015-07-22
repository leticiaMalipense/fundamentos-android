package com.example.administrador.projeto1.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.util.FormHelper;

public class SaveClientActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtAge;
    private EditText txtAddress;
    private EditText txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_save);

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
            if (FormHelper.requiredValidate(SaveClientActivity.this, txtName, txtAge, txtAddress, txtPhone)) {
                Client client = bindCliente();
                client.save();
                SaveClientActivity.this.finish();
                Toast.makeText(SaveClientActivity.this, R.string.success, Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private Client bindCliente() {
        Client client = new Client();
        client.setName(txtName.getText().toString());
        client.setAge(Integer.valueOf(txtAge.getText().toString()));
        client.setEndereco(txtAddress.getText().toString());
        client.setTelefone(txtPhone.getText().toString());
        return client;
    }

}

