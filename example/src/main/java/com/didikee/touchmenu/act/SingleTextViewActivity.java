package com.didikee.touchmenu.act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.didikee.touchmenu.R;
import com.didikee.touchmenu.view.LongTextView;
import com.didikee.touchpopmenu.PopMenuHelper;
import com.didikee.touchpopmenu.interf.OnItemLayoutLongClickListener;

public class SingleTextViewActivity extends AppCompatActivity {

    private LongTextView longTextView;
    private PopMenuHelper menuHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_text_view);
        View rootview = findViewById(R.id.activity_single_text_view);

        longTextView = ((LongTextView) findViewById(R.id.textView));

        menuHelper = new PopMenuHelper(this,rootview);
        longTextView.setOnLayoutLongClickListener(new OnItemLayoutLongClickListener() {
            @Override
            public void onLongClick(View v, float x, float y) {
                menuHelper.show(v,x,y);
            }
        });

    }
}
