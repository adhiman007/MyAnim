package com.song.myAnim.customer.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;

public class ArcEngin {

	/**生成一个圆形的进度图片*/
	public static Bitmap drawIconCountInfo(Context mContext, float progress) {
		  DisplayMetrics metric=new DisplayMetrics();
		  ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
		  
		  int w =metric.widthPixels/3;
		  int h = w;
		  Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		  Canvas mCanvas = new Canvas(bitmap);
		  mCanvas.setBitmap(bitmap);
		  //背景画笔
		  Paint paint_bg = new Paint();
		  paint_bg.setAntiAlias(true);
		  paint_bg.setStyle(Paint.Style.STROKE);
		  paint_bg.setColor(Color.rgb(171,171,171));
		  paint_bg.setStrokeWidth(w/5);
		  //前景画笔
		  Paint paint_arc = new Paint();
		  paint_arc.setAntiAlias(true);
		  paint_arc.setStyle(Paint.Style.STROKE);
		  paint_arc.setColor(Color.rgb(23, 166, 235));
		  paint_arc.setStrokeWidth(w/5);
		  //数字画笔
		  Paint paint_txt = new Paint();
		  paint_txt.setAntiAlias(true);
		  paint_txt.setStyle(Paint.Style.FILL);
		  paint_txt.setColor(0x88000000);
		  paint_txt.setTextSize((float)(w*0.15));
		  
		  //背景圆圈
		  RectF oval_bt = new RectF(18, 18, w-18, h-18);
		  mCanvas.drawArc(oval_bt, 0.0F, 360.0F, false,  paint_bg);
		  String progress_txt = String.valueOf((int)progress);
		  //计算进度百分比 换算成360进制
		  progress= progress/100*360;
		  //进度圆圈  蓝色
		  RectF oval_arc = new RectF(18, 18, w-18, h-18);
		  if(progress == 0.0f){
			  mCanvas.drawArc(oval_arc, 0.0F, 1.0f, false,  paint_arc);
		  }else{
			  mCanvas.drawArc(oval_arc, 0.0F, progress, false,  paint_arc);
		  }
		  //百分比 文字
		  if(progress_txt.length()>4){
			  mCanvas.drawText(progress_txt + "%", (float)(w*0.3), (float)(w*0.55), paint_txt);
			}else{
				mCanvas.drawText(progress_txt + "%", (float)(w*0.35), (float)(w*0.55), paint_txt);
			}
		  //保存画布，否则图像在缓存中 
		  mCanvas.save();
		  mCanvas = null;
		  return bitmap;
	}
}
