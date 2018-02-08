package com.suku.learningkids.features;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdView;
import com.suku.learningkids.R;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.addvertise.GoogleAdd;
import com.suku.learningkids.application.KidApplication;

import java.util.List;

/**
 * Created by SukamalD on 01-02-2018.
 */

public class BaseActivity extends AppCompatActivity {

    private AddManager addManager;
    private View view;


    public void displayAddBasedOnAppType(List<AddManager.AddType> addTypeList){
        view = this.findViewById(android.R.id.content);
        addManager = new AddManager(this);
        if(((KidApplication)getApplication()).mAppPreference.isPaidVersion()){
            disableAdd();
        }else{
            anableAdd();
            addManager.displayAdd(addTypeList,view);
        }
    }

    private void disableAdd(){
        findViewById(R.id.ll_banner).setVisibility(View.GONE);
    }

    private void anableAdd(){
        findViewById(R.id.ll_banner).setVisibility(View.VISIBLE);
    }

    protected void speakOut(String text) {
        TextToSpeech textToSpeech = ((KidApplication)getApplication()).textToSpeech;
        if(textToSpeech != null){
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else{
            ((KidApplication)getApplication()).initTextToSpeach();
            speakOut(text);
        }
    }

}
