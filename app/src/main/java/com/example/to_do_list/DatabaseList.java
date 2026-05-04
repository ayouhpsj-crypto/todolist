package com.example.to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.List;

public class DatabaseList extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "MyDatabase.db";

    // Table name
    private static final String TABLE_NAME = "list";

    // Table columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    // Constructor
    public DatabaseList(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    // Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // Insert a new row into the table
    public long insertData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);

        long id = db.insert(TABLE_NAME, null, values);


        return id;
    }
    // Get all Task from the table
    public List<Task> getAllTasks() {

        List<Task> tasks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();

                task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                task.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));

                tasks.add(task);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return tasks;
    }
    // Update a Task in the table
    // dosen't work so i will have to leave it for later
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());

        int result = db.update(TABLE_NAME, values, COLUMN_ID + " = ?",new String[]{String.valueOf(task.getId())}
        );

        db.close();
        return result;
    }
    // Delete a Task from the table
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(task.getId())});

        db.close();
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}