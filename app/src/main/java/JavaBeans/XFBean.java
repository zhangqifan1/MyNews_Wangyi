package JavaBeans;

import java.util.ArrayList;

/**
 * Created by 张祺钒
 * on2017/8/16.
 */
public class XFBean {

        public ArrayList<WS> ws;
        public class WS{
            public ArrayList<CW> cw;
        }
        public class CW{
            public String w;
        }

}
