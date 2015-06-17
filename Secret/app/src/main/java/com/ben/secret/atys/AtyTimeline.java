package com.ben.secret.atys;

import android.app.ListActivity;
import android.os.Bundle;

import com.ben.secret.R;

/**
 * Created by Ben on 2015/6/17.
 * 登陆后显示界面
 */
public class AtyTimeline extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);
    }
}
