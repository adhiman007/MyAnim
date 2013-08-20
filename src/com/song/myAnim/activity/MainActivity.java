package com.song.myAnim.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.RefreshableListView;
import com.song.myAnim.customer.view.RefreshableListView.OnRefreshListener;


public class MainActivity extends Activity {
	RefreshableListView lv_content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_content=(RefreshableListView) this.findViewById(R.id.lv_content);
         
        String[] stringArray = getResources().getStringArray(R.array.main_act_items);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, stringArray);
        lv_content.setAdapter(arrayAdapter);
        lv_content.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				new Timer(){}.schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								lv_content.completeRefreshing();
							}
						});
						
					}
				}, 1000);
				
			}
		});
        
        lv_content.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=null;
				if(position==1){//Anim1Activity
					intent=new Intent(MainActivity.this,Anim1Activity.class);
				}else if(position==2){//Anim2Activity
					intent=new Intent(MainActivity.this,Anim2Activity.class);
				}else if(position==3){//Anim3Activity
					intent=new Intent(MainActivity.this,Anim3Activity.class);
				}else if(position==4){//Anim4Activity
					intent=new Intent(MainActivity.this,Anim4Activity.class);
				}else if(position==5){//Anim5Activity
					intent=new Intent(MainActivity.this,Anim5Activity.class);
				}else if(position==6){//Anim6Activity
					intent=new Intent(MainActivity.this,Anim6Activity.class);
				}else if(position==7){//截屏
					intent=new Intent(MainActivity.this,Anim7Activity.class);
				}else if(position==8){//吸入动画
					intent=new Intent(MainActivity.this,Anim8Activity.class);
				}else if(position==9){//可拖动listview
					intent=new Intent(MainActivity.this,Anim9Activity.class);
				}
				if(intent!=null){
					MainActivity.this.startActivity(intent);
				}
				
				
			}});
    }
}
                    