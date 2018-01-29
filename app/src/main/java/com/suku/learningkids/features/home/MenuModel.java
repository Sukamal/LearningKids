package com.suku.learningkids.features.home;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class MenuModel {

    private String menuTitle;
    private int image;
    private int backgroundColor;
    private int textColor;
    private int actionCode;

    public MenuModel(String menuTitle,int image, int backgroundColor,int textColor){
        this.menuTitle = menuTitle;
        this.image = image;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }


    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getActionCode() {
        return actionCode;
    }

    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }
}
