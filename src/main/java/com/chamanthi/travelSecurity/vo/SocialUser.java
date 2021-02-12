package com.chamanthi.travelSecurity.vo;

public class SocialUser {
    private String sUserName;
    private String sTokenId;
    private String sUserEmail;
    private String sClientId;
    private String sAuthType;
    private String sPictureUrl;
    private String sProfileName;
    public String getsPictureUrl() {
        return sPictureUrl;
    }

    public void setsPictureUrl(String sPictureUrl) {
        this.sPictureUrl = sPictureUrl;
    }

    public String getsProfileName() {
        return sProfileName;
    }

    public void setsProfileName(String sProfileName) {
        this.sProfileName = sProfileName;
    }


    public String getsUserName() {
        return sUserName;
    }

    public void setsUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getsTokenId() {
        return sTokenId;
    }

    public void setsTokenId(String sTokenId) {
        this.sTokenId = sTokenId;
    }

    public String getsUserEmail() {
        return sUserEmail;
    }

    public void setsUserEmail(String sUserEmail) {
        this.sUserEmail = sUserEmail;
    }

    public String getsClientId() {
        return sClientId;
    }

    public void setsClientId(String sClientId) {
        this.sClientId = sClientId;
    }

    public String getsAuthType() {
        return sAuthType;
    }

    public void setsAuthType(String sAuthType) {
        this.sAuthType = sAuthType;
    }


}
