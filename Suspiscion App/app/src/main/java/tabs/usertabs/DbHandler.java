package tabs.usertabs;

/**
 * Created by KonKon on 10/28/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.sql.Date;
import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "User Report";

    private static final String TABLE_USER_REPORT = "userReportDetails";

    //on user submission:
    //location, title, brief description, timestamp
    //police also see:
    //user, their phone number, their address

    private static final String KEY_LOCATION = "location";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_USER = "user";
    private static final String KEY_PHONE_NUMBER = "phone number";
    private static final String KEY_ADDRESS = "address";

    public DbHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_REPORT_TABLE = "CREATE TABLE" + TABLE_USER_REPORT + "(" + KEY_TITLE + "TEXT,"
                + KEY_DESCRIPTION + "TEXT,"
                + KEY_TIMESTAMP + "TEXT,"
                + KEY_LOCATION + "TEXT,"
                + KEY_USER + "TEXT PRIMARY KEY," //Idea: Make user primary key to have unique entries.
                + KEY_PHONE_NUMBER + "INTEGER,"
                + KEY_ADDRESS + "TEXT" + ")";
        db.execSQL(CREATE_USER_REPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_REPORT);
        onCreate(db);
    }

    public void insertUserReport(UserReport person)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, person.getReportTitle());
        values.put(KEY_DESCRIPTION, person.getReportDescription());
        values.put(KEY_TIMESTAMP, person.getReportTime());
        values.put(KEY_LOCATION, person.getReportLocation());
        values.put(KEY_USER, person.getReportUser());
        values.put(KEY_PHONE_NUMBER, person.getReportNumber());
        values.put(KEY_ADDRESS, person.getReportAddress());

        db.insert(TABLE_USER_REPORT, null, values);
        db.close();
    }

    public void removeUserReport(UserReport report)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_REPORT, KEY_USER + "= ?",
                new String[]{report.getReportUser()});
        db.close();
    }

    public ArrayList<UserReport> getAllRecords()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_REPORT, null, null, null, null, null, null);
        ArrayList<UserReport> contacts = new ArrayList<UserReport>();
        UserReport userReport;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                UserReport report = new UserReport(cursor.getString(0), cursor.getString(1),
                        cursor.getString(3), cursor.getString(2), cursor.getString(4),
                        cursor.getInt(5), cursor.getString(6));
                contacts.add(report);
            }
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
