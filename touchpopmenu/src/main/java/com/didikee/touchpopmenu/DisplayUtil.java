package com.didikee.touchpopmenu;

import android.content.Context;

/**
 * Created by didik 
 * Created time 2016/12/27
 * Description: 
 */

public class DisplayUtil {
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context context
     *            （DisplayMetrics类中属性density）
     * @return float cast to int
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
