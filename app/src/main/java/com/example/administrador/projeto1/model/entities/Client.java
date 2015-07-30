package com.example.administrador.projeto1.model.entities;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.projeto1.model.persistence.ClientContract;
import com.example.administrador.projeto1.model.persistence.SQLiteClientRepositiry;

import java.io.Serializable;
import java.util.List;

public class Client implements Parcelable {

    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private ClientAddress address;

    public Client() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ClientAddress getAddress() {
        if (address == null) {
            return address = new ClientAddress();
        }
        return address;
    }

    public void setAddress(ClientAddress address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void save() {
        SQLiteClientRepositiry.getInstance().save(this);
    }

    public void delete() {
        SQLiteClientRepositiry.getInstance().delete(this);
    }

    public static List<Client> getAll() {
        return SQLiteClientRepositiry.getInstance().getAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (!getId().equals(client.getId())) return false;
        if (!getName().equals(client.getName())) return false;
        if (!getAge().equals(client.getAge())) return false;
        if (!getPhone().equals(client.getPhone())) return false;
        return getAddress().equals(client.getAddress());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.PHONE, client.getPhone());
        values.put(ClientContract.ZIPCODE, client.getAddress().getCep());
        values.put(ClientContract.TYPE, client.getAddress().getTipoLogradouro());
        values.put(ClientContract.STREET, client.getAddress().getLogradouro());
        values.put(ClientContract.CITY, client.getAddress().getCidade());
        values.put(ClientContract.STATE, client.getAddress().getEstado());

        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.age);
        dest.writeString(this.phone);
        dest.writeParcelable(this.address, 0);
    }

    protected Client(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.phone = in.readString();
        this.address = in.readParcelable(ClientAddress.class.getClassLoader());
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
