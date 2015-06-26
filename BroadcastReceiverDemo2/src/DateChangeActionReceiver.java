import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;


public class DateChangeActionReceiver extends Service {

	private Handler handler = new Handler();
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	registerReceiver(new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Notification n=new Notification.Builder(context).setContentTitle("DATE").setContentText("Date Chnaged").build();
			NotificationManager nm= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			nm.notify(0, n);				
		}
	}, new IntentFilter("android.intent.action.DATE_CHANGED"));	
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
