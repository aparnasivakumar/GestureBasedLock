package com.lockscreen;
import receiver.lockScreenReeiver;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;

public class LockScreenAppActivity extends Activity {

	/** Called when the activity is first created. */
	KeyguardManager.KeyguardLock k1;
	private LayoutParams layoutParams;
	VideoView video;
	String videoName;

	/* Shared Preferences */
	public static final String PREFS_NAME = "MyPrefsFile.txt";
	SharedPreferences sh_Pref;
	Editor toEdit;	
	final Context context = this;
	private boolean kidsLock;

	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG|WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onAttachedToWindow();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   	   
		sh_Pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE); 
		toEdit = sh_Pref.edit();
		toEdit.putBoolean("toggleButton", true);

		sh_Pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		kidsLock = sh_Pref.getBoolean("toggleButton", false);  	   

		System.out.println(kidsLock);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|WindowManager.LayoutParams.FLAG_FULLSCREEN);              
	    setContentView(R.layout.activity_main);   	   
		

		try{

			if(kidsLock){
				startService(new Intent(this,MyService.class));
			}
			StateListener phoneStateListener = new StateListener();
			TelephonyManager telephonyManager =(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
			telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);

			ImageView animal=(ImageView)findViewById(R.id.animal);
			animal.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					videoName="android.resource://" + getPackageName() + "/" + R.raw.animal;
					Intent videoIntent=new Intent(LockScreenAppActivity.this,VideoActivity.class);
					videoIntent.putExtra("videoName", videoName);
					startActivity(videoIntent);
				}

			});


			ImageView frozen_summer=(ImageView)findViewById(R.id.frozen_summer);
			frozen_summer.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					videoName="android.resource://" + getPackageName() + "/" + R.raw.frozen_summer;
					Intent videoIntent=new Intent(LockScreenAppActivity.this,VideoActivity.class);
					videoIntent.putExtra("videoName", videoName);
					startActivity(videoIntent);
				}

			});


			/*ImageView frozen=(ImageView)findViewById(R.id.frozen);
			frozen.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					videoName="android.resource://" + getPackageName() + "/" + R.raw.frozen;
					Intent videoIntent=new Intent(LockScreenAppActivity.this,VideoActivity.class);
					videoIntent.putExtra("videoName", videoName);
					startActivity(videoIntent);
				}

			});*/


			ImageView pig=(ImageView)findViewById(R.id.pig);
			pig.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					videoName="android.resource://" + getPackageName() + "/" + R.raw.pig;
					Intent videoIntent=new Intent(LockScreenAppActivity.this,VideoActivity.class);
					videoIntent.putExtra("videoName", videoName);
					startActivity(videoIntent);
				}

			});

			ImageView tinker=(ImageView)findViewById(R.id.tinker);
			tinker.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					videoName="android.resource://" + getPackageName() + "/" + R.raw.tinker1;
					Intent videoIntent=new Intent(LockScreenAppActivity.this,VideoActivity.class);
					videoIntent.putExtra("videoName", videoName);
					startActivity(videoIntent);
				}

			});

			ImageView twinkle=(ImageView)findViewById(R.id.twinkle);
			twinkle.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					
					finish();
					/*videoName="android.resource://" + getPackageName() + "/" + R.raw.twinkle;
					Intent videoIntent=new Intent(LockScreenAppActivity.this,VideoActivity.class);
					videoIntent.putExtra("videoName", videoName);
					startActivity(videoIntent);*/
				}

			});				
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	class StateListener extends PhoneStateListener{
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			super.onCallStateChanged(state, incomingNumber);
			switch(state){
			case TelephonyManager.CALL_STATE_RINGING:
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				System.out.println("call Activity off hook");
				finish();
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				break;
			}
		}
	};


	@Override
	public void onBackPressed() {
		// Don't allow back to dismiss.
		return;
	}

	//only used in lockdown mode
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)||(keyCode == KeyEvent.KEYCODE_POWER)||(keyCode == KeyEvent.KEYCODE_VOLUME_UP)||(keyCode == KeyEvent.KEYCODE_CAMERA)) {
			return true;
		}
		if((keyCode == KeyEvent.KEYCODE_HOME)){
			return false;
		}
		return false;
	}

	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_POWER ||(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)||(event.getKeyCode() == KeyEvent.KEYCODE_POWER)) {
			return false;
		}
		if((event.getKeyCode() == KeyEvent.KEYCODE_HOME)){
			return false;
		}
		return false;
	}


	public void onDestroy(){
		super.onDestroy();
	}

}