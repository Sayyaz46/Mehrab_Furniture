package com.example.mehrabfurniture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Test_db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_REGISTER = "REGISTER";
    private static final String COL_ID = "SL";
    private static final String COL_USERNAME = "USERNAME";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_PASSWORD = "PASSWORD";
    private static final String COL_MOBILE = "MOBILE";
    private static final String COL_ADDRESS = "ADDRESS";

    private static final String TABLE_PRODUCT = "PRODUCT";
    public static final String COL_PRODUCT_ID = "_id"; // Alias for PRODUCT_ID
    public static final String COL_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COL_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COL_PRODUCT_QUANTITY = "PRODUCT_QUANTITY";
    public static final String COL_PRODUCT_IMAGE_URI = "productImageUri";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_REGISTER + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_MOBILE + " TEXT, " +
                COL_ADDRESS + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_PRODUCT + " (" +
                COL_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_PRODUCT_NAME + " TEXT, " +
                COL_PRODUCT_PRICE + " REAL, " +
                COL_PRODUCT_QUANTITY + " INTEGER, " +
                COL_PRODUCT_IMAGE_URI + " BLOB)");
    }

    public boolean insertUser(String username, String email, String password, String mobile, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_MOBILE, mobile);
        contentValues.put(COL_ADDRESS, address);

        long result = db.insert(TABLE_REGISTER, null, contentValues);
        return result != -1;
    }

    public boolean insertProduct(String name, double price, int quantity, byte[] imageByteArray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PRODUCT_NAME, name);
        values.put(COL_PRODUCT_PRICE, price);
        values.put(COL_PRODUCT_QUANTITY, quantity);
        values.put(COL_PRODUCT_IMAGE_URI, imageByteArray); // Store image as BLOB

        long result = db.insert(TABLE_PRODUCT, null, values);
        db.close(); // Close database connection
        return result != -1;
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " +
                COL_PRODUCT_ID + ", " +
                COL_PRODUCT_NAME + ", " +
                COL_PRODUCT_PRICE + ", " +
                COL_PRODUCT_QUANTITY + ", " +
                COL_PRODUCT_IMAGE_URI +
                " FROM " + TABLE_PRODUCT +
                " ORDER BY " + COL_PRODUCT_ID + " DESC"; // Order by product ID in descending order
        return db.rawQuery(query, null);
    }

    public boolean deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PRODUCT, COL_PRODUCT_ID + "=?", new String[]{String.valueOf(productId)});
        return result > 0;
    }

    public boolean updateProduct(int productId, String name, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PRODUCT_NAME, name);
        contentValues.put(COL_PRODUCT_PRICE, price);
        contentValues.put(COL_PRODUCT_QUANTITY, quantity);

        int result = db.update(TABLE_PRODUCT, contentValues, COL_PRODUCT_ID + "=?", new String[]{String.valueOf(productId)});
        return result > 0;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID};
        String selection = COL_USERNAME + "=? and " + COL_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_REGISTER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
}
