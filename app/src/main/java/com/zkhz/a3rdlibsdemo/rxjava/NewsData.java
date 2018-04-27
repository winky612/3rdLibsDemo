package com.zkhz.a3rdlibsdemo.rxjava;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class NewsData {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"5e583e9c97c7bb8cc31dbaadf49a06ec","title":"女子吃冰棍，竟啃出条老鼠尾巴，店家：我再赔你几只冰棒吧","date":"2018-04-26 14:27","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/180426142752136.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180426/20180426_43f43250412fe4118758949e83d4f106_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180426/20180426_1294d34db40e1c1b81deb2cddd87e991_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180426/20180426_ffeeca1292a2cbeb1016122556667efb_cover_mwpm_03200403.jpg"},}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 5e583e9c97c7bb8cc31dbaadf49a06ec
             * title : 女子吃冰棍，竟啃出条老鼠尾巴，店家：我再赔你几只冰棒吧
             * date : 2018-04-26 14:27
             * category : 头条
             * author_name : 东方头条
             * url : http://mini.eastday.com/mobile/180426142752136.html
             * thumbnail_pic_s : http://02.imgmini.eastday.com/mobile/20180426/20180426_43f43250412fe4118758949e83d4f106_cover_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://02.imgmini.eastday.com/mobile/20180426/20180426_1294d34db40e1c1b81deb2cddd87e991_cover_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://02.imgmini.eastday.com/mobile/20180426/20180426_ffeeca1292a2cbeb1016122556667efb_cover_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
