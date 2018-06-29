package com.quliang.wxdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.quliang.wxdemo1.extend.AbsWeexActivity;
import com.quliang.wxdemo1.util.CommonUtils;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.ui.component.NestedContainer;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements WXSDKInstance.NestedInstanceInterceptor,IWXRenderListener {

    private ViewGroup viewGroup;
//    String url="http://10.50.30.189:8081/dist/index.js";
    String url="file://assets/dist/index.js";
    protected ViewGroup         mContainer;
    protected WXSDKInstance     mInstance;
    private static final String TAG = "AbsWeexActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createWeexInstance();
        mInstance.onActivityCreate();
        setContentView(R.layout.activity_main);
        mContainer = (ViewGroup) findViewById(R.id.container);
//        renderPageByURL("http://10.50.30.189:8081/dist/index.js",null);
//        setUrl(url);
//        renderPage();
        renderPageByURL1(url,null);
    }



    protected void createWeexInstance() {
        destoryWeexInstance();
        mInstance = new WXSDKInstance(this);
        mInstance.registerRenderListener(this);
    }

    protected void renderPageByURL1(String url, String jsonInitData) {
        CommonUtils.throwIfNull(mContainer, new RuntimeException("Can't render page, container is null"));
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, url);

        mInstance.renderByUrl(
                TAG,
                url,
                options,
                jsonInitData,
                CommonUtils.getDisplayWidth(this),
                CommonUtils.getDisplayHeight(this),
                WXRenderStrategy.APPEND_ASYNC);
    }



    @Override
    public void onCreateNestInstance(WXSDKInstance instance, NestedContainer container) {

    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (mContainer != null) {
            mContainer.removeAllViews();
            mContainer.addView(view);
        }
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mInstance != null) {
            mInstance.onActivityStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mInstance != null) {
            mInstance.onActivityResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mInstance != null) {
            mInstance.onActivityPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mInstance != null) {
            mInstance.onActivityStop();
        }
    }

    protected void destoryWeexInstance() {
        if (mInstance != null) {
            mInstance.registerRenderListener(null);
            mInstance.destroy();
            mInstance = null;
        }
    }
}
