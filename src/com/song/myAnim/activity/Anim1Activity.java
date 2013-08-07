package com.song.myAnim.activity;

import com.song.myAnim.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;


public class Anim1Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim1);
        final Button btn_result=(Button) this.findViewById(R.id.btn_result);
        btn_result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupWindow window = makePopupWindow(Anim1Activity.this);
				window.showAsDropDown(btn_result,10,10);
			}
		});
        
        
        
        
         
        
    }
  //创建一个包含自定义view的PopupWindow
    private PopupWindow makePopupWindow(Context cx) {
        final PopupWindow window = new PopupWindow(cx);
        LayoutInflater inflater=LayoutInflater.from(cx);
        View contentView=inflater.inflate(R.layout.popupwindow_layout, null);
        Button btn_result1=(Button) contentView.findViewById(R.id.btn_result1);
        btn_result1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("11111111");
				window.dismiss();
			}
		});
        
        //设置PopupWindow显示和隐藏时的动画
        window.setAnimationStyle(R.style.AnimationFade);
        //设置PopupWindow的大小（宽度和高度）
        window.setWidth(LayoutParams.WRAP_CONTENT);
        window.setHeight(LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的内容view
        window.setContentView(contentView);
            //设置PopupWindow外部区域是否可触摸
        window.setOutsideTouchable(true);
        return window;
    }
    
}
                    