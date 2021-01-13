package todolistmvp.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import todolistmvp.data.source.local.DatabaseContract;

import java.util.ArrayList;

import todolistmvp.data.model.Task;

public class TaskTableHandler implements TableHandler<Task>{
    DatabaseHelper dbHelper;

    public TaskTableHandler(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void create(Task task) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedTask.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.FeedTask.COLUMN_DESCRIPTION, task.getDesc());
        values.put(DatabaseContract.FeedTask.COLUMN_DATE, task.getDate());
        values.put(DatabaseContract.FeedTask.COLUMN_CHECK, task.getCheck());
        values.put(DatabaseContract.FeedTask.COLUMN_USER, task.getUser());

        long newRowId = db.insert(DatabaseContract.FeedTask.TABLE_NAME, null, values);
    }

    @Override
    public Task readById(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DatabaseContract.FeedTask._ID,
                DatabaseContract.FeedTask.COLUMN_TITLE,
                DatabaseContract.FeedTask.COLUMN_DESCRIPTION,
                DatabaseContract.FeedTask.COLUMN_DATE,
                DatabaseContract.FeedTask.COLUMN_CHECK,
                DatabaseContract.FeedTask.COLUMN_USER,
        };

        // Filter results WHERE "id" = id
        String selection = DatabaseContract.FeedTask._ID + " = ?";
        String[] selectionArgs = { id };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseContract.FeedTask.COLUMN_TITLE + " DESC";

        Cursor cursor = db.query(
                DatabaseContract.FeedTask.TABLE_NAME,   // The table to query
                projection,                             // The array of columns to return (pass null to get all)
                selection,                              // The columns for the WHERE clause
                selectionArgs,                          // The values for the WHERE clause
                null,                          // don't group the rows
                null,                           // don't filter by row groups
                sortOrder                               // The sort order
        );

        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(
                cursor.getLong(
                        cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask._ID))+"",
                cursor.getString(1),    //title
                cursor.getString(3),    //date
                cursor.getString(2),    //desc
                cursor.getInt(4),    //check
                cursor.getString(5)    //user
        );//description

        return task;
    }

    @Override
    public ArrayList<Task> readAll() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseContract.FeedTask.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getLong(
                                cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask._ID))+"",
                        cursor.getString(1),    //title
                        cursor.getString(2),    //date
                        cursor.getString(3),    //desc
                        cursor.getInt(4),    //check
                        cursor.getString(5)    //user
                );//description

                taskList.add(task);
            } while (cursor.moveToNext());
        }

        // return task list
        return taskList;
    }

    @Override
    public ArrayList<Task> readByUser(String user) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseContract.FeedTask.TABLE_NAME
                + " WHERE " + DatabaseContract.FeedTask.COLUMN_USER + " = '" + user +"'";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getLong(
                                cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask._ID))+"",
                        cursor.getString(1),    //title
                        cursor.getString(2),    //date
                        cursor.getString(3),    //desc
                        cursor.getInt(4),    //check
                        cursor.getString(5)    //user
                );//description

                taskList.add(task);
            } while (cursor.moveToNext());
        }

        // return task list
        return taskList;
    }

    @Override
    public void update(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // set New value
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedTask.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.FeedTask.COLUMN_DESCRIPTION, task.getDesc());
        values.put(DatabaseContract.FeedTask.COLUMN_DATE, task.getDate());
        values.put(DatabaseContract.FeedTask.COLUMN_CHECK, task.getCheck());
        values.put(DatabaseContract.FeedTask.COLUMN_USER, task.getUser());

        // Which row to update, based on the title
        String selection = DatabaseContract.FeedTask._ID + " LIKE ?";
        String[] selectionArgs = { task.getId() };

        int count = db.update(
                DatabaseContract.FeedTask.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public void delete(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = DatabaseContract.FeedTask._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { task.getId() };
        // Issue SQL statement.
        int deletedRows = db.delete(DatabaseContract.FeedTask.TABLE_NAME, selection, selectionArgs);
    }

}
