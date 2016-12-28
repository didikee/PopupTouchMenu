package com.didikee.touchpopmenu;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.didikee.touchpopmenu.interf.OnPopLayoutFingerUpListener;
import com.didikee.touchpopmenu.interf.PopHelperListener;

import java.lang.reflect.Method;

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
    public void show(View longClickView){
        Point longClickLocation = getLongClickLocation(longClickView);
        if (longClickLocation==null){
            Log.e("test","失败了");
            return;
        }
        setLocationForPopLayout(longClickLocation.x,longClickLocation.y);
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

    private Point getLongClickLocation(View longClickView){
        Class<?> viewClz=View.class;
        Class<?> viewRootImplClz;
        Point point=null;
        try {
            viewRootImplClz = Class.forName("android.view.ViewRootImpl");
            Method getViewRootImpl = viewClz.getMethod("getViewRootImpl");
            Object viewRootImplInstance = getViewRootImpl.invoke(longClickView);

            Method getLastTouchPoint = viewRootImplClz.getMethod("getLastTouchPoint",Point.class);
            point=new Point();
            getLastTouchPoint.invoke(viewRootImplInstance, point);

            Log.d("test","point X: "+point.x +"point Y: "+point.y);
        } catch (Exception e) {
            Log.d("test","get location fail...");
        }

        return point;
    }
}
