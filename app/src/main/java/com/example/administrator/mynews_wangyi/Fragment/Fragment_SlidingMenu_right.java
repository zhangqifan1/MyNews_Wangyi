package com.example.administrator.mynews_wangyi.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.mynews_wangyi.Utils.ImageLoaderUtils;
import com.example.administrator.mynews_wangyi.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import cn.sharesdk.onekeyshare.OnekeyShare;


public class Fragment_SlidingMenu_right extends Fragment implements View.OnClickListener {


    private SHARE_MEDIA platform = null;
    private ImageView mIvLog;
    /**
     * 姓名
     */
    private View inflate;
    private UMShareAPI mShareAPI;
    private Button butShare;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_fragment__sliding_menu_right, container, false);
        mShareAPI=UMShareAPI.get(getContext());
        initView();
        return inflate;
    }

    private void initView() {
        mIvLog = (ImageView) inflate.findViewById(R.id.ivLog);
        butShare = inflate.findViewById(R.id.butShare);
        mIvLog.setOnClickListener(this);
        butShare.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLog:
                platform = SHARE_MEDIA.QQ;
                mShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
                mShareAPI.deleteOauth(getActivity(), platform, umAuthListener);
                break;
            case R.id.butShare:
                //比如分享到QQ，其他平台则只需要更换平台类名，例如Wechat.NAME则是微信
                showShare();
                break;
        }
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA arg0, int arg1,
                               Map<String, String> data) {
            System.out.println("4111111111111111111111111111111111111111");
            if (data != null) {
                Set<String> keySet = data.keySet();
                //得到头像
                String iconurl = new String();
                //得到昵称
                String screenname = new String();
                for (String string : keySet) {
//                    if (string.equals("screen_name")) {
//                        //获取登录的名字
//                        screenname = data.get("screen_name");
//                        System.out.println("00000000000000000000000000000000000000"+screenname);
//                    }
                    if (string.equals("profile_image_url")) {
                        //获取登录的图片
                        iconurl = data.get("profile_image_url");
                        ImageLoaderUtils.setImageView(iconurl, getActivity(),mIvLog);
                    }
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(getContext().getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(getContext().getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    };
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("about:cehome");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("给儿子的一封信");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.mob.com/");

        // 启动分享GUI
        oks.show(getActivity());
    }



}







