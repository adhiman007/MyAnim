package com.song.myAnim.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.ArcEngin;
import com.song.myAnim.customer.view.ArcProgressView;
import com.song.myAnim.customer.view.FlipAnimation;
import com.song.myAnim.customer.view.RangeSeekBar;


public class Anim4Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim4);
        
        ImageView  iv_arc = (ImageView) this.findViewById(R.id.iv_arc);
        Bitmap bm= ArcEngin.drawIconCountInfo(this, 80);
		iv_arc.setImageBitmap(bm);

        
        
        final ArcProgressView iv_auto_increase=(ArcProgressView) this.findViewById(R.id.iv_auto_increase);
        iv_auto_increase.setProgress(80);
        final ArcProgressView iv_auto_increase2=(ArcProgressView) this.findViewById(R.id.iv_auto_increase2);
        iv_auto_increase2.setProgress(50);
        
        Animation outFlip= new FlipAnimation(0, 1f, 0.5f, 0.5f, 
				FlipAnimation.SCALE_DEFAULT, FlipAnimation.ScaleUpDownEnum.SCALE_DOWN, true);
		outFlip.setDuration(800);
		outFlip.setFillAfter(true);
		outFlip.setInterpolator(new AccelerateInterpolator()); 
		
		
		
        final LinearLayout ll_RangeSeekBar=(LinearLayout) this.findViewById(R.id.ll_RangeSeekBar);
        RangeSeekBar mRangeSeekBar=new RangeSeekBar<Number>(0, 100, this);
        mRangeSeekBar.startAnimation(outFlip);
        
        ll_RangeSeekBar.addView(mRangeSeekBar);
    } 
    
}
                    