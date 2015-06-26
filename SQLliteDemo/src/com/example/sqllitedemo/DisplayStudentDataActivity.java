package com.example.sqllitedemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class DisplayStudentDataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		ListView lv=(ListView)findViewById(R.id.listView1);
		ArrayList<String> studentlist= new ArrayList<String>();
		StudentSQLdbHelper stdhelper =new StudentSQLdbHelper(getApplicationContext());
		SQLiteDatabase sqldb= stdhelper.getReadableDatabase();
		Cursor cursor= sqldb.rawQuery("SELECT * FROM students_record",null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			String name= cursor.getString(1);
			String roll= cursor.getString(2);
			studentlist.add("Record:"+name+"#"+roll); 
			cursor.moveToNext();
		}
		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentlist);
		lv.setAdapter(myarrayAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

}
