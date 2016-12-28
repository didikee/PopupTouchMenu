package com.didikee.touchmenu.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.didikee.touchmenu.R;
import com.didikee.touchpopmenu.PopMenuHelper;

public class SingleTextViewActivity extends AppCompatActivity {

    private TextView longTextView;
    private PopMenuHelper menuHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_text_view);
        View rootview = findViewById(R.id.activity_single_text_view);

        longTextView = ((TextView) findViewById(R.id.textView));

        menuHelper = new PopMenuHelper(this,rootview);
        longTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                menuHelper.show(v);
                return true;
            }
        });

    }
}
