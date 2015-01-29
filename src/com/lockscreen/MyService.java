package com.lockscreen;

import receiver.lockScreenReeiver;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.widget.Toast;

public class MyService extends Service{
	 BroadcastReceiver mReceiver;
	// Intent myIntent;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

@Override
public void onCreate() {
	 KeyguardManager.KeyguardLock k1;
     KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
     k1= km.newKeyguardLock("IN");
     k1.disableKeyguard();
    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
    filter.addAction(Intent.ACTION_SCREEN_OFF);

     mReceiver = new lockScreenReeiver();
     registerReceiver(mReceiver, filter);

    super.onCreate();


}
@Override
public void onStart(Intent intent, int startId) {
	// TODO Auto-generated method stub

	super.onStart(intent, startId);
}


@Override
public void onDestroy() {
	unregisterReceiver(mReceiver);
	super.onDestroy();
}
}
