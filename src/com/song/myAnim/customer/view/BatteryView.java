package com.song.myAnim.customer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * 类说明
 * 
 * @author 作者： song
 * @version 创建时间：2013-7-12 上午11:31:25
 */
public class BatteryView extends ProgressBar {

	private int viewWidth;
	private int viewHeight;

	public BatteryView(Context context) {
		super(context);
		initView(context, null);
	}

	public BatteryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);

	}

	public BatteryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs);
	}

	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {
		this.setMeasuredDimension(viewWidth, viewHeight);

	}

	private void initView(Context context, AttributeSet attrs) {
		if (attrs == null) {
			viewWidth = ViewGroup.LayoutParams.MATCH_PARENT;
			viewHeight = ViewGroup.LayoutParams.MATCH_PARENT;
			return;
		}
		int[] attrsArray = new int[] {
				// android.R.attr.id, // 0
				android.R.attr.layout_width, // 0
				android.R.attr.layout_height // 1
		};

		TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
		// int id = ta.getResourceId(0 /* index of attribute in attrsArray */,
		// View.NO_ID);

		viewWidth = ta.getDimensionPixelSize(0,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		viewHeight = ta.getDimensionPixelSize(1,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		ta.recycle();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(h, w, oldw, oldh);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// 旋转
		canvas.rotate(-90);
		canvas.translate(-this.getHeight(), 0);
		super.onDraw(canvas);
	}
}
