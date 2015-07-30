package com.example.administrador.projeto1.model.persistence;

/**
 * Created by Administrador on 30/07/2015.
 */
public class UserContract  {

    public static final String TABLE = "user";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String[] COLUNS = {ID, NAME, PASSWORD};

    public static String getSqlCreatTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(" ) ");
        return sql.toString();
    }
    public static String setUser(){
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO user (id,name,password) VALUES(1,'admin','admin') ");
        return sql.toString();
    }

}
