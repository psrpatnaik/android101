package com.example.propertyanimationdemo;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	Float radius=0.0f;
	boolean repeat=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final MyView my=new MyView(getApplicationContext());
		setContentView(my);
		ValueAnimator animation = ValueAnimator.ofFloat(1f,100f);
		animation.setDuration(5000);
		animation.setRepeatCount(200);
		animation.setRepeatMode(1);
		animation.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				radius=0.0f;
				if(repeat){
					repeat=false;
				}else{
					repeat=true;
				}
					
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}
		});
		animation.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				radius = (Float) arg0.getAnimatedValue();
				my.invalidate();
			}
		});
		animation.start();

		//findViewById(R.id.surfaceView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class MyView extends View
	{
		public MyView(Context context) 
		{
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas1) 
		{
			final Canvas canvas=canvas1;
			int x = getWidth();
			int y = getHeight();
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(Color.WHITE);
			canvas.drawPaint(paint);
			// Use Color.parseColor to define HTML colors
			if(repeat)
				paint.setColor(Color.parseColor("#FF0000"));
			else
				paint.setColor(Color.parseColor("#00FF00"));
			
			canvas.drawCircle(x / 2, y / 2, radius, paint);
			super.onDraw(canvas);
		}

	}

}
