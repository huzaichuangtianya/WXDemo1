package com.quliang.wxdemo1;

import android.app.Application;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.quliang.wxdemo1.extend.ImageAdapter;
import com.quliang.wxdemo1.extend.WXEventModule;
import com.quliang.wxdemo1.util.AppConfig;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Created by quliang on 18-6-22.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        WXSDKEngine.addCustomOptions("appName", "WXSample");
//        WXSDKEngine.addCustomOptions("appGroup", "WXApp");
        WXSDKEngine.initialize(this,
                new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build()
        );
        try {
            WXSDKEngine.registerModule("event", WXEventModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
//        AppConfig.init(this);
        WeexPluginContainer.loadAll(this);
    }
}
