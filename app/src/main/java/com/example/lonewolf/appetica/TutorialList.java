package com.example.lonewolf.appetica;

import android.graphics.drawable.Drawable;

/**
 * Created by jj_lo on 08/03/2018.
 */

public class TutorialList {
    private String title;
    private Drawable image;

    public TutorialList(String name, Drawable image) {
        this.title = name;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
