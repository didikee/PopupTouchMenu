package com.didikee.touchmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.didikee.touchpopmenu.LongClickViewUtil;
import com.didikee.touchpopmenu.interf.OnItemLayoutLongClickListener;

/**
 * Created by didik 
 * Created time 2016/12/27
 * Description: 
 */

public class LongTextView extends TextView implements LongClickViewUtil.SetLongClick {

    private LongClickViewUtil longClickViewUtil;

    public LongTextView(Context context) {
        super(context);
    }

    public LongTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LongTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        longClickViewUtil = new LongClickViewUtil(this);
    }

    @Override
    public void setOnLayoutLongClickListener(OnItemLayoutLongClickListener
                                                         layoutLongClickListener) {
        longClickViewUtil.setLayoutLongClickListener(layoutLongClickListener);
    }
}
