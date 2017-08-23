package com.example.administrator.mynews_wangyi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.mynews_wangyi.Adapters.MyFragmentAdapter;
import com.example.administrator.mynews_wangyi.Fragment.FragmentTuijian;
import com.example.administrator.mynews_wangyi.Fragment.Fragment_Pindao;
import com.example.administrator.mynews_wangyi.Fragment.Fragment_Search;
import com.example.administrator.mynews_wangyi.Fragment.Fragment_Tianqi;
import com.example.administrator.mynews_wangyi.Utils.NetUtils;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareAPI;
import com.yzq.zxinglibrary.android.CaptureActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import JavaBeans.BeanTitle;

public class MainActivity extends AppCompatActivity {
    private String path = "http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news";
    public static BeanTitle bean;
    private TabLayout mTab;
    private ViewPager mVp;
    private DrawerLayout mActivityMain;
    private ActionBarDrawerToggle toggle;
    // 默认是日间模式
    private int theme = R.style.AppTheme;
    boolean flag;
    private boolean netWorkAvailable;
    private List<String> listNew;
    private ImageView mImagePindao;
    public static LinearLayout mLlLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
//        theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
        if (theme == R.style.AppTheme) {
            flag = false;
        } else {
            flag = true;
        }
        setContentView(R.layout.activity_main);
        netWorkAvailable = NetUtils.isNetWorkAvailable(this);
        if (netWorkAvailable == false) {//没网  是否去设置
            listNew = new ArrayList<>();
            SQLiteDatabase db = new MyHelper(this).getWritableDatabase();
            Cursor mytb = db.query("mytb", null, null, null, null, null, null);
            while (mytb.moveToNext()) {
                String title = mytb.getString(mytb.getColumnIndex("title"));
                listNew.add(title);
            }
        }
        mShareAPI = UMShareAPI.get(this);
        initView();
        RequestData();
        mImagePindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Pindao fragment_pindao = new Fragment_Pindao();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.ll_layout, fragment_pindao).show(fragment_pindao).addToBackStack("fragment_pindao").commit();
            }
        });
    }

    private void RequestData() {
        x.http().get(new RequestParams(path), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                bean = gson.fromJson(result, BeanTitle.class);
                initActionBar();
                initViewPager(bean);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });
    }

    private void initViewPager(BeanTitle bean) {
        //先准备数据
        List<Fragment> list = new ArrayList<>();

        for (int i = 0; i < bean.result.date.size(); i++) {
            list.add(new FragmentTuijian());
            mTab.addTab(mTab.newTab());
        }

        //创建适配器
        MyFragmentAdapter myAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        //放入数据
        myAdapter.setFragList(list);
        //mTab  设置 标题
        //适配
        mVp.setAdapter(myAdapter);
        //设置文本
        for (int i = 0; i < bean.result.date.size(); i++) {
            mTab.getTabAt(i).setText(bean.result.date.get(i).title);
        }
        if (netWorkAvailable == false) {
            for (int i = 0; i < bean.result.date.size(); i++) {
                mTab.getTabAt(i).setText(listNew.get(i));
            }
        }
    }

    private void initView() {
//        mQqIcon = (ImageView) findViewById(R.id.qqIcon);
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mActivityMain = (DrawerLayout) findViewById(R.id.activity_main);
        mTab.setupWithViewPager(mVp);
        mImagePindao = (ImageView) findViewById(R.id.image_Pindao);
        mLlLayout = (LinearLayout) findViewById(R.id.ll_layout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_tianqi:
                Fragment_Tianqi tianqi = new Fragment_Tianqi();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.set, R.anim.setout);
                transaction.replace(R.id.activity_main, tianqi).addToBackStack("tianqi").commit();
                break;
            case R.id.action_lixian://离线
                boolean netWorkAvailable = NetUtils.isNetWorkAvailable(this);
                if (netWorkAvailable == false) {//没网  是否去设置
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("提示");
                    builder.setMessage("是否设置网络");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                        }
                    });
                    builder.create().show();
                }
                //有网开始下载  保存到数据库中
                final SQLiteDatabase db = new MyHelper(MainActivity.this).getWritableDatabase();
                x.http().get(new RequestParams(path), new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        BeanTitle beana = gson.fromJson(result, BeanTitle.class);
                        List<BeanTitle.ResultBean.DateBean> date = beana.result.date;
                        for (int i = 0; i < date.size(); i++) {
                            BeanTitle.ResultBean.DateBean dateBean = date.get(i);
                            ContentValues values = new ContentValues();
                            values.put("title", dateBean.title);
                            db.insert("mytb", null, values);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                });
                break;
            case R.id.action_yejian://夜间模式

                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                flag = (theme == R.style.AppTheme) ? false : true;
                MainActivity.this.recreate();
                break;
            case R.id.action_sousuo://搜索
                Fragment_Search search = new Fragment_Search();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.setCustomAnimations(R.anim.set, R.anim.setout);
                transaction1.replace(R.id.activity_main, search).addToBackStack("sousuo").commit();

                break;
            case R.id.action_saoyisao:
               /*先申请相机权限*/
//                if (ContextCompat.checkSelfPermission(context,
//                        Manifest.permission.CAMERA)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions((Activity) context,
//                            new String[]{Manifest.permission.CAMERA},
//                            0);
//                } else {
//                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//                    startActivity(intent);
//                }
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                    //通过判断此致是否与包管理者  packageManager 中的静态常量 权限授予值  是够相等 来判断是否需要请求权限  再通过 Activity兼容
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivity(intent);
                }

            case R.id.action_shezhi:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_actionbar:
//                Toast.makeText(this, "图片", Toast.LENGTH_SHORT).show();
//                if (mActivityMain.isDrawerOpen(GravityCompat.START)) {
//                    mActivityMain.closeDrawer(GravityCompat.START);//关闭抽屉
//                } else {
//                    mActivityMain.openDrawer(GravityCompat.END);
//                }
                mActivityMain.openDrawer(GravityCompat.END);
                break;
            default:
                break;
        }
        if (mActivityMain.isDrawerOpen(GravityCompat.END)) {
            mActivityMain.closeDrawer(GravityCompat.END);//关闭抽屉
            return super.onOptionsItemSelected(item);
        }
        return toggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    MenuItem gMenuItem = null;

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        gMenuItem = menu.findItem(R.id.action_yejian);
        if (flag == true && gMenuItem != null) {
            gMenuItem.setTitle("日间");
        }
        if (flag == false && gMenuItem != null) {
            gMenuItem.setTitle("夜间");
        }
        return true;
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//设置出来图片可点击
        //ActionBar和DrawerLayout的联动
        toggle = new ActionBarDrawerToggle(MainActivity.this, mActivityMain, R.string.open, R.string.close);
        //同步状态
        toggle.syncState();
        //添加监听
        mActivityMain.addDrawerListener(toggle);
    }

    //记得要重写这个方法
    private UMShareAPI mShareAPI = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);//这里是调用menu文件夹中的main.xml，在登陆界面label右上角的三角里显示其他功能
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }


}
