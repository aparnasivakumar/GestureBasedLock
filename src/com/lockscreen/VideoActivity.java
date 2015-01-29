package com.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
 
public class VideoActivity extends Activity {
 
//String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out); 
       setContentView(R.layout.activity_video);
       Intent intent = getIntent();
       String videoName=intent.getStringExtra("videoName");
       VideoView myVideoView = (VideoView)findViewById(R.id.video_view);
//       myVideoView.setVideoURI(Uri.parse(SrcPath));
		myVideoView.setVideoURI(Uri.parse(videoName));

//       myVideoView.setMediaController(new MediaController(this));
       myVideoView.requestFocus();
       myVideoView.start();
   }
}


