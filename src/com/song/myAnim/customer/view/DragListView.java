
package com.song.myAnim.customer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.song.myAnim.R;
import com.song.myAnim.activity.Anim9Activity.EditListAdapter;

public class DragListView extends ListView {

    private ImageView dragImageView;// 被拖拽的项，其实就是一个ImageView

    private String[] moveitem;

    private int dragPosition;// 手指按下的时候列表项在listview中的位置

    private int savePosition;// 用于交换列表项用

    private int movePosition;// 手指拖动的时候，当前拖动项在列表中的位置

    private int dragPoint;// 在当前数据项中的位置

    private int dragOffset;// 当前视图和屏幕的距离(这里只使用了y方向上)

    private WindowManager windowManager;// windows窗口控制类

    private WindowManager.LayoutParams windowParams;// 用于控制拖拽项的显示的参数

    private ViewGroup itemView;

    private EditListAdapter mSelectAdapter;

    private int mEventY = 0;

    private String[] mReplaceString = new String[] {
            "", ""
    };

    public DragListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        mSelectAdapter = (EditListAdapter) getAdapter();
    }

    // 拦截touch事件，其实就是加一层控制
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            dragPosition = pointToPosition(x, y);// 判断当前xy值
                                                 // 是否在item上
                                                 // 如果在
                                                 // 返回改item的position
                                                 // 否则 返回
                                                 // INVALID_POSITION（-1）
            if (dragPosition == AdapterView.INVALID_POSITION) {
                return super.onInterceptTouchEvent(ev);
            }
            savePosition = dragPosition;
            movePosition = dragPosition;
            moveitem = mSelectAdapter.getItem(dragPosition);
            itemView = (ViewGroup) getChildAt(dragPosition - getFirstVisiblePosition());// 获取当前点击的view
            dragPoint = y - itemView.getTop();// 点击坐标-view的上边界
            dragOffset = (int) (ev.getRawY() - y);// 整个屏幕中的y坐标-listview中的y坐标,即偏移量
            View dragger = itemView.findViewById(R.id.move_item);
            if (dragger != null && x > dragger.getLeft() && x < dragger.getRight()) {
                itemView.setDrawingCacheEnabled(true);
                Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache());
                mSelectAdapter.remove(moveitem);
                mSelectAdapter.insert(mReplaceString, dragPosition);
                startDrag(bm, y);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (dragImageView != null && dragPosition != INVALID_POSITION) {
            switch (action) {
                case MotionEvent.ACTION_UP:
                    stopDrag();
                    insertLastData(movePosition);
                    mEventY = 0;
                    itemView.destroyDrawingCache();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int moveY = (int) ev.getY();
                    mEventY = moveY;
                    onDrag(moveY, (int) ev.getRawY());
                    break;
                case MotionEvent.ACTION_DOWN:
                    break;
            }
            return true;
        } else {
            return super.onTouchEvent(ev);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mEventY != 0) {
            if (mEventY != 0 && mEventY <= 0) {
                setSelectionFromTop(getFirstVisiblePosition(), getChildAt(0).getTop() + 3);
            } else if (mEventY >= getHeight()) {
                setSelectionFromTop(getFirstVisiblePosition(), getChildAt(0).getTop() - 3);
            }
        }
    }

    /**
     * 准备拖动，初始化拖动项的图像
     * 
     * @param bm
     * @param y
     */
    public void startDrag(Bitmap bm, int y) {
        stopDrag();

        windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.TOP;
        windowParams.x = 0;

        windowParams.y = y - dragPoint + dragOffset;
        windowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.windowAnimations = 0;

        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bm);
        windowManager = (WindowManager) getContext().getSystemService("window");
        windowManager.addView(imageView, windowParams);
        dragImageView = imageView;
    }

    /**
     * 停止拖动，去除拖动项的头像
     */
    public void stopDrag() {
        if (dragImageView != null) {
            windowManager.removeView(dragImageView);
            dragImageView = null;
            itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void insertLastData(int position) {
        String[] dragItem = mSelectAdapter.getItem(position);
        mSelectAdapter.remove(dragItem);
        mSelectAdapter.insert(moveitem, position);
    }

    /**
     * 拖动执行，在Move方法中执行
     */
    public void onDrag(int y, int rawY) {
        if (y < 0) { // 超出上边界，设为最小值位置0
            y = 0;
        } else if (y > getHeight()) {
            y = getHeight();

        }
        postInvalidate();
        if (dragImageView != null) {
            windowParams.alpha = 0.8f;
            // windowParams.y = y - dragPoint + dragOffset;
            windowParams.y = rawY - dragPoint;
            windowManager.updateViewLayout(dragImageView, windowParams);
        }
        // 为了避免滑动到分割线的时候，返回-1的问题
        int tempPosition = pointToPosition(0, y);
        if (tempPosition != INVALID_POSITION) {
            movePosition = tempPosition;
            onDrop(y);
        }

    }

    public void onDrop(int y) { // 根据拖动的位置在列表中放下
        if (movePosition > savePosition) {// 鼠标向下移动
            ChangeItemDown(savePosition, movePosition);
            savePosition = movePosition;
        } else if (movePosition < savePosition) {// 鼠标向上移动
            ChangeItemUp(savePosition, movePosition);
            savePosition = movePosition;
        }
    }

    private void ChangeItemDown(int save, int move) {// 向下拖动
        String[] itemStr = mSelectAdapter.getItem(move);
        mSelectAdapter.remove(mSelectAdapter.getItem(save));
        mSelectAdapter.remove(itemStr);
        mSelectAdapter.insert(itemStr, save);
        mSelectAdapter.insert(mReplaceString, move);
    }

    private void ChangeItemUp(int save, int move) {// 向上拖动
        String[] itemStr = mSelectAdapter.getItem(move);
        mSelectAdapter.remove(mSelectAdapter.getItem(save));
        mSelectAdapter.remove(itemStr);
        mSelectAdapter.insert(mReplaceString, move);
        mSelectAdapter.insert(itemStr, save);
    }
}
