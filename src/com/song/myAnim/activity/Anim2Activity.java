package com.song.myAnim.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.song.myAnim.R;
import com.song.myAnim.util.MyAnimUtil;

@SuppressLint("NewApi")
public class Anim2Activity extends Activity {
	ListView lv_content;
	TextView tv_src;
	TextView tv_dest;

	AudioManager am;
	// MediaPlayer mPlayer_selected;
	SoundPool mSoundPool;
	int streamID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anim2);
		// 获取声音管理器
		am = (AudioManager) Anim2Activity.this.getSystemService(Context.AUDIO_SERVICE);

		// mPlayer_selected = MediaPlayer.create(Anim2Activity.this,R.raw.change);
		// mPlayer_selected.setLooping(false);//设置循环播放
		int maxStream = 3;// mSoundPool最大同事支持播放的音轨
		int srcQuality = 100;// 播放声音的质量
		mSoundPool = new SoundPool(maxStream, AudioManager.STREAM_MUSIC,srcQuality);
		streamID = mSoundPool.load(this, R.raw.change, srcQuality);

		lv_content = (ListView) this.findViewById(R.id.lv_content);
		tv_src = (TextView) Anim2Activity.this.findViewById(R.id.tv_src);
		lv_content.setAdapter(new MyAdapter());
		lv_content.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// mPlayer_selected.start();
				mSoundPool.play(streamID, 1, 1, 0, 0, 1);
				tv_dest = (TextView) Anim2Activity.this
						.findViewById(R.id.tv_dest);
				TextView tv_head = (TextView) view.findViewById(R.id.tv_head);

				AnimationSet setAnim = MyAnimUtil.setAnim(tv_head, tv_dest);
				tv_src.startAnimation(setAnim);

			}
		});
	}

	class MyAdapter extends BaseAdapter {
		String[] dataArray;
		LayoutInflater inflater;

		public MyAdapter() {
			super();
			this.inflater = LayoutInflater.from(Anim2Activity.this);
			this.dataArray = Anim2Activity.this.getResources().getStringArray(
					R.array.activity_anim2_arrays);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataArray.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return dataArray[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.activity_anim2_item,
						null);
			}

			return convertView;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// if(mPlayer_selected!=null){
		// mPlayer_selected.release();
		// mPlayer_selected=null;
		// }
		if (mSoundPool != null) {
			mSoundPool.pause(streamID);
		}
	}

	//设置媒体音量
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			int current = am.getStreamVolume( AudioManager.STREAM_MUSIC  ); 
			am.setStreamVolume(AudioManager.STREAM_MUSIC , ++current, AudioManager.STREAM_MUSIC );
			return true;
		}

		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			int current = am.getStreamVolume( AudioManager.STREAM_MUSIC  ); 
			am.setStreamVolume(AudioManager.STREAM_MUSIC , --current, AudioManager.STREAM_MUSIC );
			return true;
		}else {
			return super.onKeyDown(keyCode, event);
		}

	}
}
