package com.song.myAnim.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.ScreenShot;


public class Anim7Activity extends Activity {
	Button btn_reset;
	Button btn_shot;
	ImageView iv_screen;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim7);
        iv_screen=(ImageView) this.findViewById(R.id.iv_screen);
        btn_reset=(Button) this.findViewById(R.id.btn_reset);
        btn_shot=(Button) this.findViewById(R.id.btn_shot);
        btn_reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iv_screen.setImageBitmap(null);
				iv_screen.setBackgroundResource(R.drawable.splash_small);
			}
		});
        btn_shot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Bitmap screen_bitmap = ScreenShot.takeScreenShot(Anim7Activity.this);
				  iv_screen.setImageBitmap(screen_bitmap);
			}
		});
        
      
        
        
    }
}
                    