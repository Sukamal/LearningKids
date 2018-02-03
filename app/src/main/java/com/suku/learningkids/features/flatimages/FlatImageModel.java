package com.suku.learningkids.features.flatimages;

import android.graphics.Color;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class FlatImageModel {

    private String text;
    private int image;
    private int textColor = Color.BLACK;
    private boolean isLocked = false;

    public FlatImageModel(String text, int image, boolean isLocked){
        this.text = text;
        this.image = image;
        this.isLocked = isLocked;
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

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
