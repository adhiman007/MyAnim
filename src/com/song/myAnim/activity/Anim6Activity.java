package com.song.myAnim.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.ReverseRefreshableListView.OnRefreshListener;
import com.song.myAnim.customer.view.ReverseRefreshableListView;


public class Anim6Activity extends Activity {
	ReverseRefreshableListView lv_content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim6);
        lv_content=(ReverseRefreshableListView) this.findViewById(R.id.lv_content);
         
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
        
    }
}
                    