package com.tah.touchaheart.app.database;

/**
 * Created by Lee on 8/26/2017.
 */
import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;

    private static final String DATABSE_NAME="products.db";

    public static final String TABLE_PRODUCTS="products";

    public static final String COLOUMN_ID="_id";

    public static final String COLOUMN_PRODUCTNAME="productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABSE_NAME, factory, DATABASE_VERSION);
        SQLiteDatabase db= getWritableDatabase();

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+TABLE_PRODUCTS);

        onCreate(db);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +

                COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                COLOUMN_PRODUCTNAME + " TEXT " +

                ");";

        db.execSQL(query);

    }

    public void addProduct(Products products)

    {

        ContentValues values = new ContentValues();

        values.put(COLOUMN_PRODUCTNAME,products.get_productname());

        SQLiteDatabase db= getWritableDatabase();

        db.insert(TABLE_PRODUCTS,null,values);

        db.close();

    }

    public void deleteProduct(String productname){

        SQLiteDatabase db =getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLOUMN_PRODUCTNAME + "=\"" + productname + "\";");

    }

    public String databasetostring(){

        String dbString="";

        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("productname"))!=null)

            {

                dbString+= c.getString(c.getColumnIndex("productname"));

                dbString+="\n";

            }

            c.moveToNext();

        }

        db.close();

        return dbString;

    }

    public void refreshTable() {
        SQLiteDatabase db =getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE 1");


    }

}

