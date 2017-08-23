package JavaBeans;

import java.util.List;

/**
 * Created by 张祺钒
 * on2017/8/10.
 */

public class BeanTitle {

    /**
     * reason : success
     * result : {"stat":"1","date":[{"id":6,"uri":"tt","title":"头条"},{"id":8,"uri":"shehui","title":"社会"},{"id":12,"uri":"gn","title":"国内"},{"id":13,"uri":"gj","title":"国际"},{"id":22,"uri":"yl","title":"娱乐"},{"id":23,"uri":"ty","title":"体育"},{"id":25,"uri":"js","title":"军事"},{"id":26,"uri":"kj","title":"科技"},{"id":36,"uri":"cj","title":"财经"},{"id":38,"uri":"ss","title":"时尚"},{"id":66,"uri":"bg","title":"八卦"}]}
     * error_code : 0
     */

    public String reason;
    public ResultBean result;
    public int error_code;

    public static class ResultBean {
        /**
         * stat : 1
         * date : [{"id":6,"uri":"tt","title":"头条"},{"id":8,"uri":"shehui","title":"社会"},{"id":12,"uri":"gn","title":"国内"},{"id":13,"uri":"gj","title":"国际"},{"id":22,"uri":"yl","title":"娱乐"},{"id":23,"uri":"ty","title":"体育"},{"id":25,"uri":"js","title":"军事"},{"id":26,"uri":"kj","title":"科技"},{"id":36,"uri":"cj","title":"财经"},{"id":38,"uri":"ss","title":"时尚"},{"id":66,"uri":"bg","title":"八卦"}]
         */

        public String stat;
        public List<DateBean> date;

        public static class DateBean {
            /**
             * id : 6
             * uri : tt
             * title : 头条
             */

            public int id;
            public String uri;
            public String title;
        }
    }
}
