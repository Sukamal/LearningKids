package com.suku.learningkids.features;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.suku.learningkids.R;
import com.suku.learningkids.addvertise.AddManager;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.util.AppConstant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SukamalD on 25-01-2018.
 */

public class BaseFragment extends Fragment {

    private AddManager addManager;
    private View view;
    protected boolean isPaidApp;
    protected int pagerItemPosition;
    protected ArrayList<AddManager.AddType> addTypeList;

    public void addFragment(Fragment fragment, Bundle bundle){
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        if (getActivity() != null){
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_MainContainer,fragment,fragment.getClass().getName());
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.commit();
        }
    }

    public void popFragmentBackstack(String fragmentName, boolean isInclusive){
        if(getActivity() != null){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if(isInclusive){
                fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }else{
                fragmentManager.popBackStackImmediate(fragmentName, 0);
            }
        }

    }

    public void clearAllFragment() {
        try {
            if (getActivity() != null) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                int fragmentStackEntryCount = fragmentManager.getBackStackEntryCount();

                for (int i = 0; i < fragmentStackEntryCount - 1; i++) {
                    fragmentManager.popBackStack();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disableAdd(View view){
    }


    public void displayAddBasedOnAppType(List<AddManager.AddType> addTypeList, View view){
        addManager = new AddManager(getActivity());
        this.view = view;

        if(((KidApplication)getActivity().getApplication()).mAppPreference.isPaidVersion()){
            disableAdd();
        }else{
            anableAdd();
            addManager.displayAdd(addTypeList,view);
        }
    }

    private void disableAdd(){
        view.findViewById(R.id.ll_banner).setVisibility(View.GONE);
    }

    private void anableAdd(){
        view.findViewById(R.id.ll_banner).setVisibility(View.VISIBLE);
    }

    protected void speakOut(String text) {
        TextToSpeech textToSpeech = ((KidApplication)getActivity().getApplication()).textToSpeech;
        if(textToSpeech != null){
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else{
            ((KidApplication)getActivity().getApplication()).initTextToSpeach();
            speakOut(text);
        }
    }


    protected void initTextToSpeach(){
        ((KidApplication)getActivity().getApplication()).initTextToSpeach();
    }

    protected void setApplicationMode(AppConstant.AppType mode){
        ((KidApplication)getActivity().getApplication()).mAppPreference.setAppType(mode);
    }
}
