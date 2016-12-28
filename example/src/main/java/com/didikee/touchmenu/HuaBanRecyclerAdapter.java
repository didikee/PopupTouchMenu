package com.didikee.touchmenu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private List<String> lists;
    private Context context;
    private List<Integer> heights;
//    private OnItemClickListener mListener;
    private View.OnLongClickListener mListener;
    public HuaBanRecyclerAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
        getRandomHeight(this.lists);
    }
    private void getRandomHeight(List<String> lists){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int)(200+ Math.random()*400));
        }
    }
//    public void setOnClickListener(OnItemClickListener listener){
//        this.mListener = listener;
//    }

    public void setItemLayoutLongClickListener(View.OnLongClickListener listener){
        this.mListener=listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.huaban_rv_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height = heights.get(position);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);//把params设置给item布局

        holder.mTv.setText(lists.get(position));//为控件绑定数据
        if(mListener!=null){//如果设置了监听那么它就不为空，然后回调相应的方法
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
//                    mListener.onLongClick(holder.itemView,pos);//把事件交给我们实现的接口那里处理
//                }
//            });
            (holder.itemView).setOnLongClickListener(new View.OnLongClickListener() {


                @Override
                public boolean onLongClick(View v) {
                    mListener.onLongClick(v);
                    return true;
                }

//                @Override
//                public void onLongClick(View v, float x, float y) {
//                    int pos = holder.getLayoutPosition();//得到当前点击item的位置pos
////                    mListener.onLongClick(holder.itemView,pos,x,y,actionDownForRV);//把事件交给我们实现的接口那里处理
//                    mListener.onLongClick(v,x,y);
//                }

            });
            (holder.itemView).setBackgroundColor(Color.parseColor(ColorUtil.random()));
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView mTv;
    public MyViewHolder(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.textView);
    }
}
