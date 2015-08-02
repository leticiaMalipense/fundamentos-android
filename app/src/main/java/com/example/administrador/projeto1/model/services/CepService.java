package com.example.administrador.projeto1.model.services;

import com.example.administrador.projeto1.model.entities.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private CepService() {
        super();
    }

    public static ClientAddress getAddressBy(String cep) {
        ClientAddress clientAddress = null;
        try {
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            InputStream inputStream = conn.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                clientAddress = objectMapper.readValue(inputStream, ClientAddress.class);
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientAddress;
    }

}
