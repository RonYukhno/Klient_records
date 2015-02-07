package r.yukhno.klient_records;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "MyLogs";

    private static final String DATABASE_NAME = "myDB.db";
    private static final String DATABASE_TABLE = "client_list";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_NOTE = "note";


    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE + " (_id integer primary key autoincrement, "
                    + COLUMN_FIRST_NAME + " text, "
                    + COLUMN_LAST_NAME + " text, "
                    + COLUMN_PHONE + " integer, "
                    + COLUMN_NOTE + " text);";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static String getColumnFirstName()
    {
        return COLUMN_FIRST_NAME;
    }

    public static String getColumnLastName()
    {
        return COLUMN_LAST_NAME;
    }

    public static String getColumnPhone()
    {
        return COLUMN_PHONE;
    }

    public static String getDatabaseTable()
    {
        return DATABASE_TABLE;
    }

    public static String getColumnNote() {
        return COLUMN_NOTE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");

        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
