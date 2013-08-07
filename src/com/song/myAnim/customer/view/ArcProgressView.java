package com.song.myAnim.customer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class ArcProgressView extends View {
	private Paint mPaint_back_progress;
	private Paint mPaint_progress;
	private Paint mPaint_text;

	private boolean mUseCenters_back_progress;
	private boolean mUseCenters_progress;

	private RectF mOval;
	private float progress = 0;
	private float progress_temp = 0;// 临时进度

	private int width;
	private int height;

	private Context mContext;

	public ArcProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		// initData();
	}

	public ArcProgressView(Context context) {
		super(context);
		this.mContext = context;
		// initData();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		ViewTreeObserver vto2 = ArcProgressView.this.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				ArcProgressView.this.getViewTreeObserver()
						.removeGlobalOnLayoutListener(this);
				width = ArcProgressView.this.getWidth();
				height = ArcProgressView.this.getHeight();
				

//				System.out.println("w=" + width + "h=" + height);
				measure(dip2px(mContext,width), dip2px(mContext,width));
				initData();//确定完view 大小以后，初始化绘图变量数据
			}
		});
	}


	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	private void initData() {
		mOval = new RectF();

		mPaint_back_progress = new Paint();
		mPaint_back_progress.setAntiAlias(true);// 消除锯齿
		mPaint_back_progress.setStyle(Paint.Style.STROKE);// 设置画笔笔触，（实心圆还是空心圆）
		mPaint_back_progress.setColor(0x88ababab);
		mPaint_back_progress.setStrokeWidth((float)(width*0.2));// 设置画笔 粗细
		mUseCenters_back_progress = false;// 中心是否是钟表形状

		mPaint_progress = new Paint(mPaint_back_progress);
		mPaint_progress.setStyle(Paint.Style.STROKE);
		mPaint_progress.setColor(0x8817a6eb);
		mPaint_progress.setStrokeWidth((float)(width*0.2));
		mUseCenters_progress = false;

		mPaint_text = new Paint(mPaint_back_progress);
		mPaint_text.setStyle(Paint.Style.FILL);
		mPaint_text.setColor(0x88000000); 
		mPaint_text.setStrokeWidth(2);
		mPaint_text.setTextSize((float)(width*0.15));

		mOval = new RectF((int) (width * 0.2), (int) (height * 0.2),
				(int) (width * 0.8), (int) (height * 0.8));// 圆圈是方形的内接圆
	}

	/*
	 * 调用画笔，绘制圆圈进度条
	 */
	private void drawArcs(Canvas canvas, RectF oval, boolean useCenter,
			Paint paint, float progress) {
		canvas.drawArc(oval, 0, 360, mUseCenters_back_progress,
				mPaint_back_progress);// 画一个圆圈 背景
		canvas.drawArc(oval, 0, (progress / 100 * 360), useCenter, paint);
	}

	private void drawText(Canvas canvas, Paint paint, float progress) {
		if(progress>=100){
			canvas.drawText((int) progress + "%", (float)(width*0.3), (float)(height*0.55), mPaint_text);
		}else{
			canvas.drawText((int) progress + "%", (float)(width*0.35), (float)(height*0.55), mPaint_text);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.rgb(255,255,255));// 设置画板颜色
		if (progress_temp < progress) {
			progress_temp += 1;
		}
		if(progress == 0){
			drawArcs(canvas, mOval, mUseCenters_progress, mPaint_progress,
					progress_temp+1);
		}else{
			drawArcs(canvas, mOval, mUseCenters_progress, mPaint_progress,
					progress_temp);
		}
		drawText(canvas, mPaint_progress, progress_temp);
		invalidate();
	}

	//最大值100，参数取值范围（>=0,<=100）
	public void setProgress(float pro){
		this.progress=pro;
	}
}
