package com.example.android.habariapp;

public class Habari {

    private String mWebTitle;
    private String mWebPublicationDate;
    private String mWebUrl;

    public Habari(String title, String publicationDate, String url){
        mWebTitle = title;
        mWebPublicationDate = publicationDate;
        mWebUrl = url;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}