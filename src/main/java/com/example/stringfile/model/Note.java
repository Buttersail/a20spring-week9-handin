package com.example.stringfile.model;

import java.io.Serializable;

public class Note implements Serializable {

    static final long serialVersionUID = 42L;
    public String headline;
    public String body;

    public Note(String headline, String body) {
        this.headline = headline;
        this.body = body;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBody() {
        return body;
    }
}
