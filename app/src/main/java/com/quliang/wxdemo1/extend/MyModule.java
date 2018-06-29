package com.quliang.wxdemo1.extend;

import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * Created by quliang on 18-6-24.
 */

public class MyModule extends WXModule {

    //run ui thread
    @JSMethod(uiThread = true)
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    //run JS thread
    @JSMethod (uiThread = false)
    public void fireEventSyncCall(){
        //implement your module logic here
    }

}
