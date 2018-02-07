package com.suku.learningkids.features.bodyparts;

import android.graphics.Point;

/**
 * Created by SukamalD on 07-02-2018.
 */

public class BodyPartsModel {
    private int backImage;
    private int partsImage;
    private Point point;
    private String text;
    private boolean isLocked;


    public BodyPartsModel(String text,int partsImage,Point point,int backImage){
        this.text = text;
        this.partsImage = partsImage;
        this.point = point;
        this.backImage = backImage;

    }

    public int getBackImage() {
        return backImage;
    }

    public void setBackImage(int backImage) {
        this.backImage = backImage;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getPartsImage() {
        return partsImage;
    }

    public void setPartsImage(int partsImage) {
        this.partsImage = partsImage;
    }


}
