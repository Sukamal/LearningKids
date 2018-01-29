package com.suku.learningkids.features.alphabet;

/**
 * Created by SukamalD on 29-01-2018.
 */

public class AlphabetModel {

    private String alphabetCaps;
    private String alphabetSmall;
    private String word;
    private int image;

    public AlphabetModel(String alphabetCaps,String alphabetSmall,String word,int image){
        this.alphabetCaps = alphabetCaps;
        this.alphabetSmall = alphabetSmall;
        this.word = word;
        this.image = image;
    }


    public String getAlphabetCaps() {
        return alphabetCaps;
    }

    public void setAlphabetCaps(String alphabetCaps) {
        this.alphabetCaps = alphabetCaps;
    }

    public String getAlphabetSmall() {
        return alphabetSmall;
    }

    public void setAlphabetSmall(String alphabetSmall) {
        this.alphabetSmall = alphabetSmall;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
