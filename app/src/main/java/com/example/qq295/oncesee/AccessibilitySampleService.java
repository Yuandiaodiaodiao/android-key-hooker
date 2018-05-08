package com.example.qq295.oncesee;
import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;


public class AccessibilitySampleService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        //Log.d("","233connected");
        // Toast.makeText(this,"2333",Toast.LENGTH_LONG);
        //注册广播
       // performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
        registerReceiver(mHomeKeyEventReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";
        String SYSTEM_HOME_KEY_LONG = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
          Log.d("////key!!!!!!!!",""+action);
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
              Log.d("////key!!!!!!!!",""+reason);
                if(reason.equals("assist")){performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
                return;}
                /*
                Toast.makeText(getApplicationContext(), reason, 1).show();
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home键,程序到了后台
                    Toast.makeText(getApplicationContext(), "home", 1).show();
                }else if(TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){
                    performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
                    //表示长按home键,显示最近使用的程序列表

                }*/
            }
        }
    };
    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        int key=event.getKeyCode();
        Log.d("key!!!!!!!!",""+key);
      //  performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
        switch(key){
            case KeyEvent.KEYCODE_HOME:{break;}
            case KeyEvent.KEYCODE_BACK:{
               // performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
                break;}

            case KeyEvent.ACTION_MULTIPLE:{
                Log.d("1","////keyACTION_MULTIPLE");
             //   performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
                break;}
          default:break;
        }
        return super.onKeyEvent(event);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
       // performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
        //performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
        Log.d("1","onaccessevent");
        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
      //  String pkgName = event.getPackageName().toString();
     //   int eventType = event.getEventType();
        // AccessibilityOperator封装了辅助功能的界面查找与模拟点击事件等操作

    }
    @Override
    public void onInterrupt() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("","destroy");

    }
}