package com.suku.learningkids.models;

import android.graphics.Color;

/**
 * Created by SukamalD on 05-02-2018.
 */

public class ItemModel {

    private String heading;
    private String subheading;
    private String subheading2;
    private int image;
    private int imageIcon;
    private int textColor = Color.BLACK;
    private boolean isLocked = false;

    public ItemModel(String heading,int image,boolean isLocked){
        this(heading,null,image,0,isLocked);
    }

    public ItemModel(String heading,int image,int imageIcon,boolean isLocked){
        this(heading,null,image,imageIcon,isLocked);
    }

    public ItemModel(String heading,String subheading,int image,int imageIcon,boolean isLocked){
        this(heading,subheading,null,image,imageIcon,isLocked);
    }

    public ItemModel(String heading,String subheading,String subheading2,int image,int imageIcon,boolean isLocked){
        this.heading = heading;
        this.subheading = subheading;
        this.subheading2 = subheading2;
        this.image = image;
        this.imageIcon = imageIcon;
        this.isLocked = isLocked;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
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

    public String getSubheading2() {
        return subheading2;
    }

    public void setSubheading2(String subheading2) {
        this.subheading2 = subheading2;
    }
}
