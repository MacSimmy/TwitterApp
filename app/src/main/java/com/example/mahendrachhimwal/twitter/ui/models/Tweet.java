package com.example.mahendrachhimwal.twitter.ui.models;

import java.io.Serializable;

/**
 * Created by mahendra.chhimwal on 26/06/2015.
 */
public class Tweet implements Serializable{
    private static final long serialVersionUID = 1L;
    private String mId;
    private String mTitle;
    private String mBody;

    public String getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getBody() {
        return this.mBody;
    }

    public void setId(String id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setBody(String body) {
        mBody = body;
    }
}
