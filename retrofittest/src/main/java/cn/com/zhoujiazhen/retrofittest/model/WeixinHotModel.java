package cn.com.zhoujiazhen.retrofittest.model;

import java.util.List;

import cn.com.zhoujiazhen.retrofittest.base.BaseModel;

/**
 * Created by zhoujiazhen on 16/5/26.
 */
public class WeixinHotModel extends BaseModel{


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-05-26","title":"【游记】亲身探访明洞HAHA烤肉店","description":"韩国me2day","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-5729265.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5NDg1NTU2MA==&idx=8&mid=2653134817&sn=f11e415075e815448e51a27ff5d27746"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-05-26
     * title : 【游记】亲身探访明洞HAHA烤肉店
     * description : 韩国me2day
     * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-5729265.jpg/640
     * url : http://mp.weixin.qq.com/s?__biz=MjM5NDg1NTU2MA==&idx=8&mid=2653134817&sn=f11e415075e815448e51a27ff5d27746
     */

    private List<NewslistEntity> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistEntity> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistEntity> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistEntity {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}