package com.example.administrator.mynews_wangyi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入xxx
     */
    private EditText mEtName;
    /**
     * 加载
     */
    private Button mButLoad;
    private ProgressBar mBar;
    private WebView mWv;
    /**
     * 刷新
     */
    private Button mButRef;
    /**
     * 前进
     */
    private Button mButQianjin;
    /**
     * 后退
     */
    private Button mButBack;
    private RelativeLayout mRelLayoyt;
    private LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String name = getIntent().getStringExtra("name");
        initView();
        mEtName.setText(name);
        WebClientInit();
        WebViewClientInit();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.etName);
        mButLoad = (Button) findViewById(R.id.butLoad);
        mButLoad.setOnClickListener(this);
        mBar = (ProgressBar) findViewById(R.id.bar);
        mWv = (WebView) findViewById(R.id.wv);
        mButRef = (Button) findViewById(R.id.butRef);
        mButRef.setOnClickListener(this);
        mButQianjin = (Button) findViewById(R.id.butQianjin);
        mButQianjin.setOnClickListener(this);
        mButBack = (Button) findViewById(R.id.butBack);
        mButBack.setOnClickListener(this);
        mRelLayoyt = (RelativeLayout) findViewById(R.id.rel_layoyt);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butLoad:
                String edString = getEdString(mEtName);
                if (edString == null) {
                    Toast.makeText(WebViewActivity.this, "亲,空的", Toast.LENGTH_SHORT).show();
                }
//                mWv.loadUrl("http://www." + edString + "/");
                mWv.loadUrl(edString);
                break;
            case R.id.butRef:
                if(mWv.canGoForward()){
                    mWv.reload();
                }

                break;
            case R.id.butQianjin:
                mWv.goForward();
                break;
            case R.id.butBack:
                if(mWv.canGoBack()){
                    mWv.goBack();
                }

                break;
        }
    }

    private void WebViewClientInit() {
        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);

            }
        });

        //UI改变时的监听
        mWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mBar.setVisibility(View.VISIBLE);
                mBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

    }


    private void WebViewInit() {
        WebSettings settings = mWv.getSettings();
        settings.setJavaScriptEnabled(true);//支持Js
        settings.setSupportZoom(true);//设置支持缩放
        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);//设置默认缩放大小
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(true);//不显示图片  只加载文字
    }

    private void WebClientInit() {
        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);

            }
        });

        //UI改变时的监听
        mWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mBar.setVisibility(View.VISIBLE);
                mBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public String getEdString(EditText editText) {
        String s = editText.getText().toString();
        if (s != null && !s.equals("")) {
            return s;
        }
        return null;
    }

    /**
     * 设置点击返回按钮，跳转到上一个html页面，而不是退出当前activity
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWv.canGoBack()) {
            mWv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
