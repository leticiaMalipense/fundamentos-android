package com.example.administrador.projeto1.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable, Parcelable {
    private String name;
    private Integer age;
    private String address;
    private String phone;

    public Client(){
        super();
    }

    public Client(Parcel in){
        super();
        readToParcel(in);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void save() {
        MemoryClientRepository.getInstance().save(this);
    }

    public void delete() {
        MemoryClientRepository.getInstance().delete(this);
    }

    public static List<Client> getAll() {
        return MemoryClientRepository.getInstance().getAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null)
            return false;
        return !(getAge() != null ? !getAge().equals(client.getAge()) : client.getAge() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName() + "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name == null ? "" : name);
        dest.writeString(phone == null ? "" : phone);
        dest.writeInt(age == null ? -1 : age);
        dest.writeString(address == null ? "" : address);
    }

    public void readToParcel(Parcel in) {
        name = in.readString();
        phone = in.readString();
        int partialAge = in.readInt();
        age = partialAge == -1 ? null : partialAge;
        address = in.readString();
    }
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

}
