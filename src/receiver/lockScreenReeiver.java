package receiver;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.widget.Toast;

import com.lockscreen.LockScreenAppActivity;

public class lockScreenReeiver extends BroadcastReceiver  {
	public static boolean wasScreenOn = true;
	public static final String PREFS_NAME = "MyPrefsFile.txt";
	SharedPreferences sh_Pref;
	Editor toEdit;	
	private boolean kidsLock;


	@Override
	public void onReceive(Context context, Intent intent) {

		sh_Pref = context.getSharedPreferences(PREFS_NAME,0);
		kidsLock = sh_Pref.getBoolean("toggleButton", false);  	   

		if(kidsLock){

			if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
				wasScreenOn=false;
				Intent intent11 = new Intent(context,LockScreenAppActivity.class);
				intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent11);

			} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
				wasScreenOn=true;
				Intent intent11 = new Intent(context,LockScreenAppActivity.class);
				intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
			else if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
			{
				Intent intent11 = new Intent(context, LockScreenAppActivity.class);
				intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent11);
			}
		}
	}
}
