package com.example.administrador.projeto1.model.persistence;

import android.content.ContentValues;

public class ClientContract {
    public static final String TABLE = "client";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String AGE = "age";

    public static final String PHONE = "phone";

    public static final String ADDRESS = "address";

    public static final String[] COLUNS = {ID, NAME, AGE, PHONE, ADDRESS};

    public static String getSqlCreatTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(AGE + " TEXT ,");
        sql.append(PHONE + " TEXT ,");
        sql.append(ADDRESS + " TEXT ");
        sql.append(" ) ");
        return sql.toString();
    }

}
