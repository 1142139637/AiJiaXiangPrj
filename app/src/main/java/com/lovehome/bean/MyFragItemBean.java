package com.lovehome.bean;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyFragItemBean {
    public int img ;//图片地址
    public String content ;//文字内容
    public int id ;
    public int getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MyFragItemBean{" +
                "img=" + img +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}
