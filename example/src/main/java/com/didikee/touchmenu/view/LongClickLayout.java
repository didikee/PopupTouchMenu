package com.didikee.touchmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.didikee.touchpopmenu.LongClickViewUtil;
import com.didikee.touchpopmenu.interf.OnItemLayoutLongClickListener;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class LongClickLayout extends FrameLayout implements LongClickViewUtil.SetLongClick{
    private LongClickViewUtil longClickViewUtil;

//    private float longClickX;
//    private float longClickY;
//    private OnItemLayoutLongClickListener layoutLongClickListener;

    public LongClickLayout(Context context) {
        this(context,null);
    }

    public LongClickLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LongClickLayout(Context context, final AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        this.setOnLongClickListener(new OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (layoutLongClickListener!=null)layoutLongClickListener.onLongClick(v,longClickX,longClickY);
//                return true;
//            }
//        });
        longClickViewUtil = new LongClickViewUtil(this);
    }

    @Override
    public void setOnLayoutLongClickListener(OnItemLayoutLongClickListener
                                                         layoutLongClickListener) {
        longClickViewUtil.setLayoutLongClickListener(layoutLongClickListener);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("test","child long click item: "+event.getAction());
//        if (event.getAction()==MotionEvent.ACTION_DOWN){
//            longClickX=event.getRawX();
//            longClickY=event.getRawY();
//        }
//        return super.onTouchEvent(event);
//    }

//    public void setOnLayoutLongClickListener(OnItemLayoutLongClickListener layoutLongClickListener){
//        this.layoutLongClickListener=layoutLongClickListener;
//    }
}
