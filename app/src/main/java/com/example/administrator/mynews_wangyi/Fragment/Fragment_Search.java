package com.example.administrator.mynews_wangyi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.mynews_wangyi.Adapters.ListViewAdapter_Search;
import com.example.administrator.mynews_wangyi.EdtextChangedListener;
import com.example.administrator.mynews_wangyi.R;
import JavaBeans.XFBean;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.mynews_wangyi.R.id.etInput;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Search extends Fragment implements View.OnClickListener {
    private ListView mListView;
    public static final String[] NAMES = new String[]{"你好","宋江", "卢俊义", "吴用",
            "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深",
            "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘",
            "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍",
            " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪",
            "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方",
            "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充",
            "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿",
            "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩",
            "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立",
            "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜",
            "时迁", "段景柱", "小凡哥"};
    String result="";
    private StringBuilder mStringBuilder;
    private EditText editText;
    private Button speak;
    private ListView lv;
    private List<String> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment__search, container, false);

        editText = inflate.findViewById(etInput);
        speak = inflate.findViewById(R.id.speak);
        lv = inflate.findViewById(R.id.listView_Search);
        speak.setOnClickListener(this);
        //准备数据
        list = new ArrayList<>();
        for (int i = 0; i <NAMES.length ; i++) {
            list.add(NAMES[i]);
        }
        editText.addTextChangedListener(new EdtextChangedListener(){
            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                super.onTextChanged(charSequence, i, i1, i2);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListViewAdapter_Search adapterSearch = new ListViewAdapter_Search(list, getContext(),charSequence.toString());
                        lv.setAdapter(adapterSearch);
                    }
                });
            }
        });
        return inflate;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.speak:
                Listen();
                break;
            default:
                break;
        }

    }
    /**
     * 把声音转换为文字
     *
     */
    public void Listen() {
        //1.创建RecognizerDialog对象,第二个参数就是一个初始化的监听器,我们用不上就设置为null
        RecognizerDialog mDialog = new RecognizerDialog(getContext(), null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//设置为中文模式
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");//设置普通话模式
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //mDialog.setParameter("asr_sch", "1");
        //mDialog.setParameter("nlp_version", "2.0");
        //创建一个装每次解析数据的容器
        mStringBuilder = new StringBuilder();
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override//识别成功执行,参数recognizerResult 识别的结果,Json格式的字符串
            //第二参数 b:等于true时会话结束.方法才不会继续回调
            //一般情况下通过onResult接口多次返回结果,完整识别内容是多次结果累加的
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                //拿到讯飞识别的结果
                String resultString = recognizerResult.getResultString();
/*                System.out.println("讯飞识别的结果 " + resultString);
                System.out.println("b参数是什么 " + b);*/
                //自定义解析bean数据的方法,得到解析数据
                String content= parseData(resultString);
//                System.out.println("解析后的数据 "+ content);
                mStringBuilder.append(content);
                //对参数2b进行判断,如果为true,代表这个方法不会对调,那么我们容器的数据转为字符串,拿来使用即可
                if(b){
                    result = mStringBuilder.toString();
                    editText.setText(result);

                    //回答对象,在没有匹配到用户说的话,默认输出语句
//                    String anwser="不好意思,请大声点，老子耳朵不好使";
//                    if(result.contains("你好")){
//                        anwser="你好,我是你的智能语音助手,很高兴为你服务";
//                    }else if(result.contains("安卓学习哪家强")){
//                        anwser="我有橘麻麦皮不知当讲不当讲";
//                    }else if(result.contains("美女")){
//                        String [] anwserList=new String[]{"你是坏人不和你玩","小助手很纯洁,不要说我听不懂的话","小助手我就是美女,主人","500元,小助手帮主人找美女一起打英雄联盟"};
//                        int random = (int)(Math.random() * anwserList.length);
//                        anwser=anwserList[random];
//                    }
//                    shuo(anwser);
                }
            }
            @Override//识别失败执行的方法,speechError错误码
            public void onError(SpeechError speechError) {
                System.out.println("错误码 " + speechError);
            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }
    /***一个自定义的方法***/
    private String parseData(String resultString){
        //创建gson对象.记得要关联一下gson.jar包,方可以使用
        Gson gson = new Gson();
        //参数1 String类型的json数据   参数2.存放json数据对应的bean类
        XFBean xfBean = gson.fromJson(resultString, XFBean.class);
        //创建集合,用来存放bean类里的对象
        ArrayList<XFBean.WS> ws=xfBean.ws;
        //创建一个容器,用来存放从每个集合里拿到的数据,使用StringBUndle效率高
        StringBuilder stringBuilder = new StringBuilder();
        for (XFBean.WS w : ws) {
            String text= w.cw.get(0).w;
            stringBuilder.append(text);
        }
        //把容器内的数据转换为字符串返回出去
        return stringBuilder.toString();
    }


    /******************************语音合成的点击事件代码*****************************/


    /**
     * 把文字转换为声音
     *
     * @param view
     */
    public void Talk(View view) {
        shuo("钒哥威武霸气一统江湖，千秋万载，万载千秋");
    }

    public void shuo(String result){
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(getContext(), null);
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        // 设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端,这些功能用到了讯飞服务器,所以要有网络
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
        // mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        // 3.开始合成,第一个参数就是转换成声音的文字,自定义,第二个参数就是合成监听器对象,我们不需要对声音有什么特殊处理,就传null
        mTts.startSpeaking(result, null);
    }


}
