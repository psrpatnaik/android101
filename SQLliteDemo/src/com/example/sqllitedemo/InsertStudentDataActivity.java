package com.example.sqllitedemo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

class StudentSQLdbHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "students.db";
	private static final int DATABASE_VERSION = 1;	

	public StudentSQLdbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE students_record ( _ID  integer primary key,name text, roll integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS students_record");
		 onCreate(db);
	}
}

public class InsertStudentDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Button insert=(Button)findViewById(R.id.button1);
        
        insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				StudentSQLdbHelper stdhelper=new StudentSQLdbHelper(getApplicationContext());
				SQLiteDatabase sqldb= stdhelper.getWritableDatabase();
				ContentValues cv=new ContentValues();
				String name=((EditText)findViewById(R.id.editText1)).getText().toString();
				String roll=((EditText)findViewById(R.id.editText2)).getText().toString().trim();
				cv.put("name", name);
				cv.put("roll", Integer.valueOf(roll));
				sqldb.insert("students_record", null, cv);
				stdhelper.close();
				Toast.makeText(getApplicationContext(), "Data Inserted !!", Toast.LENGTH_SHORT).show();
			}
		});
        
        Button display=(Button)findViewById(R.id.button2);
        display.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(getApplicationContext(), DisplayStudentDataActivity.class);
			    startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
        
}

class StudentContentProvider extends ContentProvider
{
	StudentSQLdbHelper stdhelper=null;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		 stdhelper=new StudentSQLdbHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder sqlqb=new SQLiteQueryBuilder();
		sqlqb.setTables("students_record");
		SQLiteDatabase sqldb= stdhelper.getReadableDatabase();
		Cursor c=sqlqb.query(sqldb, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}


