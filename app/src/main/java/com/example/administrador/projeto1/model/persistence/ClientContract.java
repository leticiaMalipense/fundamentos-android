package com.example.administrador.projeto1.model.persistence;

import android.content.ContentValues;

public class ClientContract {
    public static final String TABLE = "client";

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String AGE = "age";

    public static final String PHONE = "phone";

    public static final String ZIPCODE = "zipcode";

    public static final String TYPE = "type";

    public static final String STREET = "street";

    public static final String CITY = "cidade";

    public static final String STATE = "state";

    public static final String[] COLUNS = {ID, NAME, AGE, PHONE};

    public static String getSqlCreatTable() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(NAME + " TEXT, ");
        sql.append(AGE + " TEXT ,");
        sql.append(PHONE + " TEXT ,");
        sql.append(ZIPCODE + " TEXT ,");
        sql.append(TYPE + " TEXT ,");
        sql.append(STREET + " TEXT ,");
        sql.append(CITY + " TEXT ,");
        sql.append(STATE + " TEXT ");
        sql.append(" ) ");
        return sql.toString();
    }

}
