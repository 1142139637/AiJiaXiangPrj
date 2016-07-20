package com.lovehome.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pengdan on 2016/7/20.
 */

public class LoginBean implements Parcelable {
    /**
     * u_addr : 长沙望城
     * u_id : 1
     * u_phone : 15116186074
     * u_pwd : 123456
     */

    private DataBean data;
    /**
     * data : {"u_addr":"长沙望城","u_id":1,"u_phone":"15116186074","u_pwd":"123456"}
     * msg : 登录成功
     * state : 1
     */

    private String msg;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean implements Parcelable {
        private String u_addr;
        private int u_id;
        private String u_phone;
        private String u_pwd;

        public String getU_addr() {
            return u_addr;
        }

        public void setU_addr(String u_addr) {
            this.u_addr = u_addr;
        }

        public int getU_id() {
            return u_id;
        }

        public void setU_id(int u_id) {
            this.u_id = u_id;
        }

        public String getU_phone() {
            return u_phone;
        }

        public void setU_phone(String u_phone) {
            this.u_phone = u_phone;
        }

        public String getU_pwd() {
            return u_pwd;
        }

        public void setU_pwd(String u_pwd) {
            this.u_pwd = u_pwd;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.u_addr);
            dest.writeInt(this.u_id);
            dest.writeString(this.u_phone);
            dest.writeString(this.u_pwd);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.u_addr = in.readString();
            this.u_id = in.readInt();
            this.u_phone = in.readString();
            this.u_pwd = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.data, flags);
        dest.writeString(this.msg);
        dest.writeInt(this.state);
    }

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.msg = in.readString();
        this.state = in.readInt();
    }

    public static final Parcelable.Creator<LoginBean> CREATOR = new Parcelable.Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}
