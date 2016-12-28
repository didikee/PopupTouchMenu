package com.didikee.touchpopmenu;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import com.didikee.touchpopmenu.interf.PopHelperListener;

/**
 * Created by didik 
 * Created time 2016/12/27
 * Description: 
 */

public class MotionEventUtil {
    private boolean isPopShow=false;
    private final PopMenuHelper popMenuHelper;

    public MotionEventUtil(PopMenuHelper popMenuHelper) {
        this.popMenuHelper = popMenuHelper;

        doRecyclerView();
        doPopHelper();
    }

    public void updatePopState(boolean isPopShow){
        this.isPopShow=isPopShow;
    }

    public boolean getPopState(){
        return isPopShow;
    }

    private void doPopHelper() {
        popMenuHelper.setPopListener(new PopHelperListener() {
            @Override
            public void onPopDismiss() {
                isPopShow=false;
                popMenuHelper.getScrollableView().dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
            }
        });
    }

    private void doRecyclerView() {
        popMenuHelper.getScrollableView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("test","parent: "+event.getAction());
                if ((event.getAction()==MotionEvent.ACTION_UP ||event.getAction()==MotionEvent.ACTION_MOVE) && isPopShow){
                    popMenuHelper.setMotionForPopLayout(event);
                    return true;
                }
                return false;
            }
        });

    }

}
