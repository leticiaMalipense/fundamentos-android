package com.example.administrador.projeto1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClientListAdapter extends BaseAdapter {

    private List<Cliente> clientList;
    private Activity context;

    public ClientListAdapter(Activity context, List<Cliente> clientList) {
        this.context = context;
        this.clientList = clientList;
    }

    @Override
    public int getCount() {
        return clientList.size();
    }

    @Override
    public Cliente getItem(int position) {
        return clientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.client_list, parent, false);
        Cliente cliente = getItem(position);

        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(cliente.getName());

        TextView textViewAge = (TextView) view.findViewById(R.id.textViewAge);
        textViewAge.setText(cliente.getAge().toString());

        return view;
    }
}
