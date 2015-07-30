package com.example.administrador.projeto1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.entities.User;
import com.example.administrador.projeto1.model.persistence.SQLiteUserRepository;
import com.example.administrador.projeto1.util.FormHelper;

import org.apache.http.protocol.HTTP;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "USER_PARAM";

    Button buttonLogin;
    private EditText txtName;
    private EditText txtPassword;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindFildes();
        bindLoginButton();
    }
    private void bindFildes() {
        txtName = (EditText) findViewById(R.id.login);
        txtPassword = (EditText) findViewById(R.id.password);
    }

    private void bindLoginButton() {
        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindUser();
                if(FormHelper.requiredValidate(LoginActivity.this, txtName, txtPassword)) {
                    if (SQLiteUserRepository.login(user)) {
                        Intent intent = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.invalid, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void bindUser() {
        if (user == null) {
            user = new User();
        }
        user.setName(txtName.getText().toString());
        user.setPassword(txtPassword.getText().toString());
    }
}

