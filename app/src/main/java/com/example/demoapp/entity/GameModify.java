package com.example.demoapp.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demoapp.db.DBHelper;
import com.example.demoapp.models.Game;

public class GameModify {
    public static final String QUERY_CREATE_TABLE = "create table game (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\ttitle varchar(50),\n" +
            "\tcontent text,\n" +
            "\tprice float\n" +
            ")";


    public static Cursor findAll() {
        String sql = "select * from game";

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        return cursor;
    }

    public static void insert(Game game) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", game.getTitle());
        values.put("content", game.getContent());
        values.put("price", game.getPrice());

        sqLiteDatabase.insert("game", null, values);
    }

    public static void save(Game game) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", game.getTitle());
        values.put("content", game.getContent());
        values.put("price", game.getPrice());

        sqLiteDatabase.update("game", values, " _id = " + game.get_id(), null);
    }

    public static void delete(int id) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        sqLiteDatabase.delete("game", " _id = " + id, null);
    }
}
