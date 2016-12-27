package com.didikee.touchmenu.act;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;

import com.didikee.touchmenu.HuaBanDivier;
import com.didikee.touchmenu.HuaBanRecyclerAdapter;
import com.didikee.touchmenu.R;
import com.didikee.touchpopmenu.PopMenuHelper;
import com.didikee.touchpopmenu.interf.OnItemLayoutLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class RvStaggleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> lists;
    private HuaBanRecyclerAdapter adapter;
    private PopMenuHelper pop;
//    private MotionEventUtil eventUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_staggle);
        setBarStyle();
        initData();
        recyclerView = ((RecyclerView) findViewById(R.id.rv));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL){
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }
        };
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.addItemDecoration(new HuaBanDivier(this));
        adapter = new HuaBanRecyclerAdapter(this,lists);
        recyclerView.setAdapter(adapter);
        pop = new PopMenuHelper(this,recyclerView);
        adapter.setItemLayoutLongClickListener(new OnItemLayoutLongClickListener() {
            @Override
            public void onLongClick(View v, float x, float y) {
                pop.show(v,x,y);
            }
        });
    }


    private void initData() {
        lists = new ArrayList();
        for (int i = 0; i < 100; i++) {
            lists.add("" + i);
        }
    }
    public void setBarStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
