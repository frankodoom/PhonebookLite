package net.accedegh.studentregistration.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDbHelper extends SQLiteOpenHelper {

     private static final String  DATABASE = "StudentRegDb";
     private static final String TABLE ="Student";
     private static final String COLUMN_ID  = "_id";
     private static final String COLUMN_NAME = "Name";
     private static final String COLUMN_PHONE ="Phone";
     private static final String COLUMN_EMAIL ="Email";
    private static final String COLUMN_TAG ="Tag";


     public StudentDbHelper(Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

     @Override
     public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Student " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,Name Text,Phone Text, Email Text, Tag Text)");

    }

     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE);
        onCreate(db);
    }


     public boolean addStudent(String name, String phone, String email, String tag) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_TAG, tag);
        long result = db.insert(TABLE, null, cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

     public Cursor getAllStudents(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + TABLE,null );
       return data;

     }


     public Cursor getAllStudetById(String id){
        SQLiteDatabase db = getWritableDatabase();
        String query ="select * from " + TABLE + " where _id = ? ";
        Cursor data = db.rawQuery(query, new String[]{id});
        return data;
    }

     public void deleteStudent(String studentId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " +TABLE + " where _id = " +studentId + "");
        //db.delete()

     }

  public Cursor searchStudent(String queryText){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[1];
        args[0] = "%"+queryText+"%";
        String query ="select * from Student where Name Like ?";
        Cursor cursor = db.rawQuery(query,args);
        return cursor;
    }


    public void UpdateStudent(String id, String name, String phone, String email, String tag){
      SQLiteDatabase db = getWritableDatabase();
      ContentValues cv = new ContentValues();
      String where = "_id=?";
      cv.put("Name", name);
      cv.put("Email", email);
      cv.put("Phone",phone);
      cv.put("Tag",tag);
      db.update(TABLE,cv,"_id=?",new String[]{id});
  }
}
