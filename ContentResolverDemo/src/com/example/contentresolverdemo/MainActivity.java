package com.example.contentresolverdemo;

import java.util.ArrayList;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv=(ListView)findViewById(R.id.listView1);
		ArrayList<String> studentlist= new ArrayList<String>();
		ContentResolver cr=getContentResolver();
		Cursor cursor=cr.query(Uri.parse("content://com.example.sqllitedemo.StudentContentProvider"), new String[]{"name","roll"}, null, null, null);
		
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
