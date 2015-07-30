package com.example.administrador.projeto1.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.administrador.projeto1.model.entities.AppUtil;
import com.example.administrador.projeto1.model.entities.User;

import java.sql.Statement;

/**
 * Created by Administrador on 30/07/2015.
 */
public class SQLiteUserRepository {

    public static boolean login(User user) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        /*
        "SELECT COUNT(*) FROM user WHERE name='" + user.getName()
                + "' and password='" + user.getPassword() +"'"
        */

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS,
                " name = '" + user.getName() + "' AND password = '" + user.getPassword() + "' ",
                null, null, null, ClientContract.NAME);

        return cursor.getCount() > 0;
    }
}
