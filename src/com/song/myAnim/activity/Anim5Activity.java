package com.song.myAnim.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.Rotate3dAnimation;


public class Anim5Activity extends Activity {
//	private ViewFlipper timeFlipper;
    private ViewGroup mContainer;
    ListView  lv_content;
    ImageView  iv_demo;
    
    private boolean is_positive=true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim5);
        
        mContainer = (ViewGroup) this.findViewById(R.id.container);
        lv_content = (ListView) this.findViewById(R.id.lv_content);
        iv_demo = (ImageView) this.findViewById(R.id.iv_demo);
        
        
        String[] stringArray = getResources().getStringArray(R.array.activity_anim2_arrays);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, stringArray);
        lv_content.setAdapter(arrayAdapter);
        lv_content.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				is_positive=false;
//				iv_demo.setImageResource(R.drawable.fanqie_footer_icon);
		        applyRotation(position, 0, 90);
			}
		});
		 
//		AnimationFactory.flipTransition(timeFlipper, FlipDirection.LEFT_RIGHT, false);
        iv_demo.setClickable(true);
        iv_demo.setFocusable(true);
        iv_demo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				is_positive=true;
				applyRotation(-1, 180, 90);
			}
		});

        // Since we are caching large views, we want to keep their cache
        // between each animation
        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
		
		
    } 
    
    private void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f-20;//减去状态栏的高度

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView());

        mContainer.startAnimation(rotation);
    }
    private final class DisplayNextView implements Animation.AnimationListener {
        public void onAnimationStart(Animation animation) {}
        public void onAnimationEnd(Animation animation) {
            mContainer.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
		            final float centerX = mContainer.getWidth() / 2.0f;
		            final float centerY = mContainer.getHeight() / 2.0f;
		            Rotate3dAnimation rotation;
		            
		            if (!is_positive) {
		                lv_content.setVisibility(View.GONE);
		                iv_demo.setVisibility(View.VISIBLE);
		            	 
		                iv_demo.requestFocus();

		                rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
		            } else {
		            	iv_demo.setVisibility(View.GONE);
		            	lv_content.setVisibility(View.VISIBLE);
		            	lv_content.requestFocus();

		                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
		            }

		            rotation.setDuration(500);
		            rotation.setFillAfter(true);
		            rotation.setInterpolator(new DecelerateInterpolator());

		            mContainer.startAnimation(rotation);
		        
				}
			});
        }

        public void onAnimationRepeat(Animation animation) {}
    }
}
                    