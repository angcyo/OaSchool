package com.angcyo.oaschool.mode.bean;

import java.util.List;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class TitleListResult extends  BaseResult {

    /**
     * titles : [{"gonggaoList":[{"id":"277","author":"魏勉","title":"bbbbbbbbbbbbbbbbbbbbbbbb","datetime":"9-11 ","isnew":"0"},{"id":"267","author":"魏勉","title":"vvvvvvvvvvvvvvvvvvvv","datetime":"9-11 ","isnew":"1"},{"id":"257","author":"魏勉","title":"cccccccccccccccccccccccccccccc","datetime":"9-11 ","isnew":"0"},{"id":"247","author":"魏勉","title":"xxxxxxxxxxxxxxxxxx","datetime":"9-11 ","isnew":"0"},{"id":"237","author":"魏勉","title":"zzzzzzzzzzzzzzzzzzzz","datetime":"9-11 ","isnew":"0"},{"id":"227","author":"秦春黎","title":"111111111111111111111111","datetime":"9-11 ","isnew":"1"},{"id":"217","author":"秦春黎","title":"fffffffffffffff","datetime":"9-11 ","isnew":"1"},{"id":"207","author":"秦春黎","title":"ddddddddddddddddddddddddddddddddddd","datetime":"9-11 ","isnew":"0"},{"id":"197","author":"秦春黎","title":"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb","datetime":"9-11 ","isnew":"1"},{"id":"187","author":"秦春黎","title":"ddddddddddddddd","datetime":"9-11 ","isnew":"0"},{"id":"177","author":"魏勉","title":"请各位老师上交科技节小结 ","datetime":"11-24 ","isnew":"0"},{"id":"167","author":"校长室","title":"关于中学阅读方法指导课的听课通知","datetime":"9-1 ","isnew":"0"},{"id":"157","author":"魏勉","title":"第七周学校工作提点 ","datetime":"9-1 ","isnew":"0"},{"id":"147","author":"魏勉","title":"关于征集优秀学生事例的通知","datetime":"9-1 ","isnew":"0"},{"id":"137","author":"魏勉","title":"广东省教育强镇（乡）督导验收方案","datetime":"9-1 ","isnew":"0"},{"id":"107","author":"陈小琴","title":"接天莲叶无穷碧,暑假西湖游","datetime":"9-1 ","isnew":"0"},{"id":"97","author":"陈小琴","title":"09年高考各科复习备考窍门点拨","datetime":"9-1 ","isnew":"0"},{"id":"87","author":"陈小琴","title":"改变人生的100句至理明言","datetime":"9-1 ","isnew":"0"},{"id":"77","author":"陈小琴","title":"幼师毕业男生近一半改行","datetime":"9-1 ","isnew":"1"},{"id":"67","author":"陈小琴","title":"关于坚决遏制部分城市房价过快上涨的通知","datetime":"9-1 ","isnew":"1"}]}]
     */

    private List<TitlesEntity> titles;

    public void setTitles(List<TitlesEntity> titles) {
        this.titles = titles;
    }

    public List<TitlesEntity> getTitles() {
        return titles;
    }

    public static class TitlesEntity {
        /**
         * gonggaoList : [{"id":"277","author":"魏勉","title":"bbbbbbbbbbbbbbbbbbbbbbbb","datetime":"9-11 ","isnew":"0"},{"id":"267","author":"魏勉","title":"vvvvvvvvvvvvvvvvvvvv","datetime":"9-11 ","isnew":"1"},{"id":"257","author":"魏勉","title":"cccccccccccccccccccccccccccccc","datetime":"9-11 ","isnew":"0"},{"id":"247","author":"魏勉","title":"xxxxxxxxxxxxxxxxxx","datetime":"9-11 ","isnew":"0"},{"id":"237","author":"魏勉","title":"zzzzzzzzzzzzzzzzzzzz","datetime":"9-11 ","isnew":"0"},{"id":"227","author":"秦春黎","title":"111111111111111111111111","datetime":"9-11 ","isnew":"1"},{"id":"217","author":"秦春黎","title":"fffffffffffffff","datetime":"9-11 ","isnew":"1"},{"id":"207","author":"秦春黎","title":"ddddddddddddddddddddddddddddddddddd","datetime":"9-11 ","isnew":"0"},{"id":"197","author":"秦春黎","title":"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb","datetime":"9-11 ","isnew":"1"},{"id":"187","author":"秦春黎","title":"ddddddddddddddd","datetime":"9-11 ","isnew":"0"},{"id":"177","author":"魏勉","title":"请各位老师上交科技节小结 ","datetime":"11-24 ","isnew":"0"},{"id":"167","author":"校长室","title":"关于中学阅读方法指导课的听课通知","datetime":"9-1 ","isnew":"0"},{"id":"157","author":"魏勉","title":"第七周学校工作提点 ","datetime":"9-1 ","isnew":"0"},{"id":"147","author":"魏勉","title":"关于征集优秀学生事例的通知","datetime":"9-1 ","isnew":"0"},{"id":"137","author":"魏勉","title":"广东省教育强镇（乡）督导验收方案","datetime":"9-1 ","isnew":"0"},{"id":"107","author":"陈小琴","title":"接天莲叶无穷碧,暑假西湖游","datetime":"9-1 ","isnew":"0"},{"id":"97","author":"陈小琴","title":"09年高考各科复习备考窍门点拨","datetime":"9-1 ","isnew":"0"},{"id":"87","author":"陈小琴","title":"改变人生的100句至理明言","datetime":"9-1 ","isnew":"0"},{"id":"77","author":"陈小琴","title":"幼师毕业男生近一半改行","datetime":"9-1 ","isnew":"1"},{"id":"67","author":"陈小琴","title":"关于坚决遏制部分城市房价过快上涨的通知","datetime":"9-1 ","isnew":"1"}]
         */

        private List<GonggaoListEntity> gonggaoList;

        public void setGonggaoList(List<GonggaoListEntity> gonggaoList) {
            this.gonggaoList = gonggaoList;
        }

        public List<GonggaoListEntity> getGonggaoList() {
            return gonggaoList;
        }

        public static class GonggaoListEntity {
            /**
             * id : 277
             * author : 魏勉
             * title : bbbbbbbbbbbbbbbbbbbbbbbb
             * datetime : 9-11
             * isnew : 0
             */

            private String id;
            private String author;
            private String title;
            private String datetime;
            private String isnew;

            public void setId(String id) {
                this.id = id;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public void setIsnew(String isnew) {
                this.isnew = isnew;
            }

            public String getId() {
                return id;
            }

            public String getAuthor() {
                return author;
            }

            public String getTitle() {
                return title;
            }

            public String getDatetime() {
                return datetime;
            }

            public String getIsnew() {
                return isnew;
            }
        }
    }
}
