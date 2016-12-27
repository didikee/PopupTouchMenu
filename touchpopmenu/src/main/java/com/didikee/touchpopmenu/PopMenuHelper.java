package com.didikee.touchpopmenu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.didikee.touchpopmenu.interf.OnPopLayoutFingerUpListener;
import com.didikee.touchpopmenu.interf.PopHelperListener;

/**
 * Created by didik 
 * Created time 2016/12/27
 * Description: 
 */

public class PopMenuHelper {
    private Activity activity;
    private PopupWindow popupWindow;
    private PopHelperListener popListener;
    private PopLayout popLayout;
    private View outsideScrollableView;
    private MotionEventUtil eventUtil;

    public PopMenuHelper(Activity activity,View outsideScrollableView) {
        this.activity = activity;
        this.outsideScrollableView=outsideScrollableView;
        initPop();
        eventUtil = new MotionEventUtil(this);
    }

    public View getScrollableView(){
        return outsideScrollableView;
    }


    private void initPop() {
        popupWindow = new PopupWindow(activity);
        popLayout = new PopLayout(activity);
        popupWindow = new PopupWindow(popLayout, ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT,true);
        //设置可以点击
        popupWindow.setTouchable(true);
//        popupWindow.setAnimationStyle(R.style.pop_translate);
        ColorDrawable background=new ColorDrawable(Color.parseColor("#33000000"));
        popupWindow.setBackgroundDrawable(background);
        popupWindow.setOutsideTouchable(true);

        /**
         * 让pop全屏
         */
        popupWindow.setClippingEnabled(false);
        popLayout.setPopLayoutListener(new OnPopLayoutFingerUpListener() {
            @Override
            public void onFingerUp() {
                popupWindow.dismiss();
                if (popListener!=null){
                    popListener.onPopDismiss();
                }
            }
        });
    }
    public void show(View longClickView,float x, float y){
        setLocationForPopLayout(x,y);
        eventUtil.updatePopState(true);
        longClickView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
        getScrollableView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
        popupWindow.showAtLocation(activity.findViewById(android.R.id.content), Gravity.CENTER,0,0);
    }

    public void setLocationForPopLayout(float x,float y){
        popLayout.setLocation(x,y);
    }

    public void setMotionForPopLayout(MotionEvent event){
        popLayout.onTouchEvent(event);
    }

    public void setPopListener(PopHelperListener popListener){
        this.popListener=popListener;
    }
}
