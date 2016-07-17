package com.lovehome.bean;

/**
 * Created by JH on 2016/7/15.
 */
public class ReleaseTypeBean {
    private int releaseTypeImgID;
    private String releaseTypeName;

    public ReleaseTypeBean(int releaseTypeImgUrl, String releaseTypeName) {
        this.releaseTypeImgID = releaseTypeImgUrl;
        this.releaseTypeName = releaseTypeName;
    }

    public int getReleaseTypeImgID() {
        return releaseTypeImgID;
    }

    public void setReleaseTypeImgID(int releaseTypeImgID) {
        this.releaseTypeImgID = releaseTypeImgID;
    }

    public String getReleaseTypeName() {
        return releaseTypeName;
    }

    public void setReleaseTypeName(String releaseTypeName) {
        this.releaseTypeName = releaseTypeName;
    }


}
