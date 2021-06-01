package com.example.lenovo.studentsgrievancemanagemant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME="StudentGrievancesManagement";
    public static final String TABLE_NAME="register";
    public static final String TABLE2_NAME = "Eregister";
    public static final String TABLE3_NAME = "AcademicComplaints";
    public static final String TABLE4_NAME = "AdmissionComplaints";
    public static final String TABLE5_NAME = "HostelComplaints";
    public static final String TABLE6_NAME = "OtherComplaints";
    public static final String TABLE7_NAME = "AcSol";
    public static final String TABLE8_NAME = "AdSol";
    public static final String TABLE9_NAME = "HSol";
    public static final String TABLE10_NAME = "OSol";
    public static final String COL_1= "ID";
    public static final String COL_2 = "FullName";
    //public static final String COL_3 = "Number";
    public static final String COL_4 = "Username";
    public static final String COL_5 = "Password";
    public static final String C_6 = "Message";
    public static final String c_7 = "Solution";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register(ID INTEGER PRIMARY KEY AUTOINCREMENT, FullName TEXT, Username TEXT, Password TEXT)");
        db.execSQL("CREATE TABLE Eregister(ID INTEGER PRIMARY KEY AUTOINCREMENT, FullName TEXT, Username TEXT, Password TEXT)");
        db.execSQL("CREATE TABLE AcademicComplaints(ID INTEGER PRIMARY KEY AUTOINCREMENT,  Message TEXT)");
        db.execSQL("CREATE TABLE AdmissionComplaints(ID INTEGER PRIMARY KEY AUTOINCREMENT,  Message TEXT)");
        db.execSQL("CREATE TABLE HostelComplaints(ID INTEGER PRIMARY KEY AUTOINCREMENT,  Message TEXT)");
        db.execSQL("CREATE TABLE OtherComplaints(ID INTEGER PRIMARY KEY AUTOINCREMENT,  Message TEXT)");
        db.execSQL("CREATE TABLE AcSol(ID INTEGER PRIMARY KEY AUTOINCREMENT, Solution TEXT)");
        db.execSQL("CREATE TABLE AdSol(ID INTEGER PRIMARY KEY AUTOINCREMENT, Solution TEXT)");
        db.execSQL("CREATE TABLE HSol(ID INTEGER PRIMARY KEY AUTOINCREMENT,  Solution TEXT)");
        db.execSQL("CREATE TABLE OSol(ID INTEGER PRIMARY KEY AUTOINCREMENT,  Solution TEXT)");

        //db.execSQL( "UPDATE " + TABLE3_NAME + " SET " + COL_1 + " = " + 100000 );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE7_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE8_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE9_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE10_NAME);

        onCreate(db);
    }

    /*public boolean addSolution(String Solution) {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues cv = new ContentValues(  );
        cv.put( c_7, Solution );
        Log.d(TAG, "addSolution: Adding " + Solution + " to " + TABLE3_NAME);
        long result = db.insert(TABLE3_NAME, null, cv);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getSolution(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Solution FROM AcademicComplaints WHERE ID='"+ id+"'";
        Cursor solution = db.rawQuery( query, null );
        return solution;
    }*/
    public boolean addAcSol(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(c_7 , Message);
        // contentValues.put(COL_2, FullName);
        // contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE7_NAME);

        long res = db.insert(TABLE7_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public boolean addAdSol(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(c_7 , Message);
        // contentValues.put(COL_2, FullName);
        // contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE8_NAME);

        long res = db.insert(TABLE8_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public boolean addHSol(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(c_7 , Message);
        // contentValues.put(COL_2, FullName);
        // contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE9_NAME);

        long res = db.insert(TABLE9_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public boolean addOSol(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(c_7 , Message);
        // contentValues.put(COL_2, FullName);
        // contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE10_NAME);

        long res = db.insert(TABLE10_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public Cursor getAcSol() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE7_NAME;
        //cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{user,password});
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getAdSol() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE8_NAME;
        //cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{user,password});
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getHSol() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE9_NAME;
        //cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{user,password});
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getOSol() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE10_NAME;
        //cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{user,password});
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean addData(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_6 , Message);
       // contentValues.put(COL_2, FullName);
       // contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE3_NAME);

        long res = db.insert(TABLE3_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
       String query = "SELECT * FROM " + TABLE3_NAME;
        //cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{user,password});
        Cursor data = db.rawQuery(query, null);
        return data;
    }
   /* public Cursor AddData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT * FROM " + TABLE_NAME;
        //String query = "SELECT " + COL_2 + " " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + ;

        Cursor data = db.rawQuery(query, null);
        return data;
    }*/
    public boolean addData1(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_6 , Message);
        //contentValues.put(COL_2, FullName);
        //contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE4_NAME);

        long res = db.insert(TABLE4_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public Cursor getData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public boolean addData2(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_6 , Message);
        //contentValues.put(COL_2, FullName);
        //contentValues.put( COL_3, Number );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE5_NAME);

        long res = db.insert(TABLE5_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public Cursor getData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE5_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public boolean addData3(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_6 , Message);
        //contentValues.put(COL_2, FullName);
        //contentValues.put( COL_4, userName );
        Log.d(TAG, "addData: Adding " + Message + " to " + TABLE6_NAME);

        long res = db.insert(TABLE6_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public Cursor getData3() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT *  FROM " + TABLE6_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateEntry(String newPassword,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM register WHERE Username='"+ name+"'", null);
        if(c.moveToFirst()) {
            db.execSQL( "UPDATE register  SET  Password='" + newPassword + "' WHERE Username ='" + name + "'" );
        }
    }
    public void updateEntry1(String newPassword,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM Eregister WHERE Username='"+ name+"'", null);
        if(c.moveToFirst()) {
            db.execSQL( "UPDATE Eregister  SET  Password='" + newPassword + "' WHERE Username ='" + name + "'" );
        }
    }
    //Cursor cur= myDB.rawQuery("UPDATE "+UserDBHandler.USER_TABLE+" SET "+UserDBHandler.PASSWORD+" = '"+newPassword+"' WHERE "+
            //UserDBHandler.USER_NAME+" = ?",new String[]{name});
}
