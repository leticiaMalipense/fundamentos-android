package com.example.administrador.projeto1.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ClientAddress implements Serializable, Parcelable {
    private String cep;

    @JsonProperty("tipoDeLogradouro")
    private String tipoLogradouro;

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public ClientAddress() {

    }

    public ClientAddress(String cep, String tipo,
                         String logradouro, String cidade, String estado) {
        this.cep = cep;
        this.tipoLogradouro = tipo;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String type) {
        this.tipoLogradouro = type;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cep == null ? "" : cep);
        dest.writeString(tipoLogradouro == null ? "" : tipoLogradouro);
        dest.writeString(logradouro == null ? "" : logradouro);
        dest.writeString(cidade == null ? "" : cidade);
        dest.writeString(estado == null ? "" : estado);
    }

    public void readToParcel(Parcel in) {
        cep = in.readString();
        tipoLogradouro = in.readString();
        logradouro = in.readString();
        cidade = in.readString();
        estado = in.readString();
    }

}
