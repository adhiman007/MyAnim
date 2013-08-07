package com.song.myAnim.util; 

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/** 
 *  类说明 
 * @author  作者： song  
 * @version 创建时间：2013-7-10 上午10:30:11 
 */
public class MyAnimUtil {

	
	public static AnimationSet setAnim(View v_current, View v_dest) {
		int ANIM_DURATION = 800;// 动画持续时间
		int ANIM_REPEAT = 0;// 动画重复次数

		AnimationSet set = new AnimationSet(false);
		int location_cur[] = new int[2];
		int location_dest[] = new int[2];
		v_current.getLocationInWindow(location_cur);
		v_dest.getLocationInWindow(location_dest);
		int x_distance = location_dest[0]+(2*v_current.getWidth()) - location_cur[0];

		// 水平方向的位置移动
		TranslateAnimation transX = new TranslateAnimation(0, x_distance-(v_dest.getWidth()/2), 0, 0);
		transX.setInterpolator(new DecelerateInterpolator());
		transX.setRepeatCount(ANIM_REPEAT);
		// 垂直方向的位置移动
		TranslateAnimation tranY = new TranslateAnimation(0, 0, location_cur[1]-(3*v_current.getHeight())-v_current.getWidth()/2,location_dest[1]-(v_dest.getHeight()));
		tranY.setInterpolator(new AccelerateInterpolator());
		tranY.setRepeatCount(ANIM_REPEAT);
//		// 渐隐
		AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.1f);
		alphaAnim.setInterpolator(new AccelerateInterpolator());
		alphaAnim.setRepeatCount(ANIM_REPEAT);
//		// 缩放
		ScaleAnimation scaleAnim = new ScaleAnimation(1f, 0f, 1f, 0f,
				Animation.RELATIVE_TO_PARENT, 1f,
				Animation.RELATIVE_TO_PARENT, 1f);
		scaleAnim.setInterpolator(new LinearInterpolator());
		scaleAnim.setRepeatCount(ANIM_REPEAT);

		set.addAnimation(tranY);
		set.addAnimation(transX);
//		set.addAnimation(alphaAnim);
//		set.addAnimation(scaleAnim);
		set.setDuration(ANIM_DURATION);
		return set;
	}
}
