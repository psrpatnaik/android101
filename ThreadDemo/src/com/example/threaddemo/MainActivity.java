package com.example.threaddemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b=(Button)findViewById(R.id.button1);
		final TextView tv=(TextView)findViewById(R.id.textView1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				/*
				 * Conventional Way of Creating Threads ,  NOT TO BE USED in Android.
				 new Thread(( new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
							tv.setText("Output from Thread");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				})).start(); 

				 */
				tv.post(( new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
							tv.setText("Output from Thread");
							Intent i= getIntent();
							Resources res= getResources();
							//res.getDrawable(R.drawable.slru);
							//tv.setBackgroundResource(R.drawable.ic_launcher);
							((ImageView)findViewById(R.id.imageView1)).setImageDrawable(res.getDrawable(R.drawable.slru));
							Notification n=new Notification.Builder(getApplication()).setContentTitle("Demo").setContentText("Demo text.....").setSmallIcon(R.drawable.ic_launcher).build();
							NotificationManager nm= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
							nm.notify(0, n);
							//tv.setBackground(res.getDrawable(R.drawable.slru));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}));
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
