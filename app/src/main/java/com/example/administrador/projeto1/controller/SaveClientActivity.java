package com.example.administrador.projeto1.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.projeto1.R;
import com.example.administrador.projeto1.model.entities.Client;
import com.example.administrador.projeto1.model.entities.ClientAddress;
import com.example.administrador.projeto1.model.services.CepService;
import com.example.administrador.projeto1.util.FormHelper;

public class SaveClientActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";

    private EditText txtName;
    private EditText txtAge;
    private EditText txtPhone;
    private EditText txtCep;
    private Button btnFindCep;
    private EditText txtTipoDeLogradouro;
    private EditText txtLogradouro;
    private EditText txtCity;
    private EditText txtEstado;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_save);

        bindFildes();
        getParameters();
    }

    private void bindFildes() {
        txtName = (EditText) findViewById(R.id.txtName);

        txtName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        txtName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (txtName.getRight() - txtName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });


        txtAge = (EditText) findViewById(R.id.txtAge);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtCep = (EditText) findViewById(R.id.txtCep);
        txtTipoDeLogradouro = (EditText) findViewById(R.id.txtTipoDeLogradouro);
        txtLogradouro = (EditText) findViewById(R.id.txtLogradouro);
        txtCity = (EditText) findViewById(R.id.txtCity);
        txtEstado = (EditText) findViewById(R.id.txtEstado);
        bindButton();
    }

    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    txtName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    txtPhone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void bindButton() {
        btnFindCep = (Button) findViewById(R.id.btnFindCep);
        btnFindCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAddressByCep getAddressByCep = new GetAddressByCep();
                getAddressByCep.execute(txtCep.getText().toString());
            }
        });
    }

    private void getParameters() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if (client == null) {
                throw new IllegalArgumentException();
            }
            bindForm(client);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {
            if (FormHelper.requiredValidate(SaveClientActivity.this, txtName, txtAge, txtPhone)) {
                bindCliente();
                client.save();
                SaveClientActivity.this.finish();
                Toast.makeText(SaveClientActivity.this, R.string.success, Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindCliente() {
        if (client == null) {
            client = new Client();
        }
        client.setName(txtName.getText().toString());
        client.setAge(Integer.valueOf(txtAge.getText().toString()));
        client.setPhone(txtPhone.getText().toString());
        client.setAddress(
                new ClientAddress(txtCep.getText().toString(),
                        txtTipoDeLogradouro.getText().toString(),
                        txtLogradouro.getText().toString(),
                        txtCity.getText().toString(),
                        txtEstado.getText().toString()));
    }

    private void bindForm(Client client) {
        txtName.setText(client.getName());
        txtAge.setText(client.getAge().toString());
        txtPhone.setText(client.getPhone());
        address(client);
    }

    private void address(Client client) {
        txtCep.setText(client.getAddress().getCep());
        txtTipoDeLogradouro.setText(client.getAddress().getTipoLogradouro());
        txtLogradouro.setText(client.getAddress().getLogradouro());
        txtCity.setText(client.getAddress().getCidade());
        txtEstado.setText(client.getAddress().getEstado());
    }

    private class GetAddressByCep extends AsyncTask<String, Void, ClientAddress> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(SaveClientActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();

        }

        @Override
        protected ClientAddress doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddress clientAddress) {
            progressDialog.dismiss();

            Client client = new Client();
            client.setAddress(clientAddress);
            address(client);
        }
    }


}

