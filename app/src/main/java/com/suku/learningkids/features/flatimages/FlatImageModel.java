package com.suku.learningkids.features.flatimages;

import android.graphics.Color;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class FlatImageModel {

    private String text;
    private int image;
    private int textColor = Color.BLACK;

    public FlatImageModel(String text, int image){
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String word) {
        this.text = word;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
