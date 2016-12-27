package com.didikee.touchpopmenu;

import android.view.MotionEvent;
import android.view.View;

import com.didikee.touchpopmenu.interf.OnItemLayoutLongClickListener;

/**
 * Created by didik 
 * Created time 2016/12/27
 * Description: 
 */

public class LongClickViewUtil {
    private final View longClickView;
    private float longClickX;
    private float longClickY;
    private OnItemLayoutLongClickListener layoutLongClickListener;

    public LongClickViewUtil(View longClickView) {
        this.longClickView = longClickView;
        bind();
    }

    private void bind() {
        longClickView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (layoutLongClickListener!=null)layoutLongClickListener.onLongClick(v,longClickX,longClickY);
                return true;
            }
        });

        longClickView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    longClickX=event.getRawX();
                    longClickY=event.getRawY();
                }
                return false;
            }
        });
    }
    public void setLayoutLongClickListener(OnItemLayoutLongClickListener layoutLongClickListener){
        this.layoutLongClickListener=layoutLongClickListener;
    }

    public interface SetLongClick{
        void setOnLayoutLongClickListener(OnItemLayoutLongClickListener layoutLongClickListener);
    }
}
