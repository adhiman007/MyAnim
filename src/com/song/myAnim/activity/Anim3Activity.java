package com.song.myAnim.activity;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.ArcEngin;
import com.song.myAnim.customer.view.BatteryView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Anim3Activity extends Activity {
	Handler handler = new Handler();
	TextView tv_result;
	boolean run = true;
	int count = 0;
	
	
	Runnable task = new Runnable() {

		public void run() {
			// TODO Auto-generated method stub
			if (run) {
				handler.postDelayed(this, 1000);
				count++;
			}
			tv_result.setText("" + count);
		}
	};
	
	BatteryView my_battery_progress;
	ProgressBar my_progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anim3);
		final Button btn_start = (Button) this.findViewById(R.id.btn_start);
		Button btn_stop = (Button) this.findViewById(R.id.btn_stop);
		my_progress = (ProgressBar) this.findViewById(R.id.my_progress);
		tv_result = (TextView) this.findViewById(R.id.tv_result);
		
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler.postDelayed(task, 1000);
				my_progress.setVisibility(View.VISIBLE);
				btn_start.setEnabled(false);
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler.removeCallbacks(task);
				my_progress.setVisibility(View.INVISIBLE);
				btn_start.setEnabled(true);
			}
		});
		my_battery_progress = (BatteryView) this.findViewById(R.id.my_battery_progress);
		my_battery_progress.setProgress(40);
		
		
	}

}
