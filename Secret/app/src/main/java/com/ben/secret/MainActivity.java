package com.ben.secret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ben.secret.atys.AtyTimeline;

/**
 *主Activity为入口根据相关规则跳转到不同Activity
 * 1.跳转登陆页面：当前token没有，或过期
 * 2.跳转到消息评论显示：token存在
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //界面跳转
        String token = Config.getCachedToken(this);

        startActivity(new Intent(this, AtyTimeline.class));

//        if (token != null){
//            Intent i = new Intent(this, AtyTimeline.class);
//
//            //send "token" in order that Timeline could commit with Server
//            i.putExtra(Config.KEY_TOKEN, token);
//            startActivity(i);
//        } else {
//            startActivity(new Intent(this, AtyLogin.class));
//        }

        finish(); //back won't show MainActivity
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
