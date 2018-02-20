package com.suku.learningkids.features.splash;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suku.learningkids.R;
import com.suku.learningkids.application.KidApplication;
import com.suku.learningkids.features.home.HomeActivity;
import com.suku.learningkids.network.NetworkService;
import com.suku.learningkids.storage.AppPreference;
import com.suku.learningkids.util.AppConstant;
import com.suku.learningkids.util.AppDialog;
import com.suku.learningkids.util.UtilClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final int MSG_DISPLAY_NEXT_CHARACTER = 101;
    private static final int MSG_DISPLAY_NEXT_SCREEN = 102;
    private static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;


    private boolean isAppRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        checkDevicePermission();
        initializeSplash();
    }

    private void checkDevicePermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            executeUserPermissionTree();
        }else{
            initializeSplash();
        }
    }

    private void initializeSplash(){
        checkDeviceRegistration();
        startZoomInAnimation();
        if(!isAppRegistered){
            getAppRegisterInfo();
        }

    }

    private void checkDeviceRegistration(){
        AppPreference appPreference = ((KidApplication)getApplication()).mAppPreference;
        if(appPreference.getStringPref(AppConstant.Preferences.DEVICE_ID.name()) == null){
            appPreference.saveStringPref(AppConstant.Preferences.DEVICE_ID.name(), UtilClass.getAndroidId(SplashActivity.this));
        }
        isAppRegistered = appPreference.isAppRegistered();

    }

    private void startZoomInAnimation(){
        ImageView imageView = findViewById(R.id.iv_splash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
        imageView.startAnimation(animation);

        settext(getString(R.string.app_name));

    }

    private void settext(final String s){
        final TextView textView1 = findViewById(R.id.tv_text1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/kid1.ttf");
        textView1.setTypeface(custom_font);
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == MSG_DISPLAY_NEXT_CHARACTER){

                    if(i[0] < s.length() ){

                        try {
                            char c= s.charAt(i[0]);
                            Log.d("Strange",""+c);
                            textView1.append(String.valueOf(c));
                            i[0]++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }else if(msg.what == MSG_DISPLAY_NEXT_SCREEN){
                    if(isAppRegistered){
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    }
//
                }

            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {

                if (i[0] < length ) {
                    handler.sendEmptyMessage(MSG_DISPLAY_NEXT_CHARACTER);
                }else{
                    handler.sendEmptyMessage(MSG_DISPLAY_NEXT_SCREEN);
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 300);
    }

    private void getAppRegisterInfo(){

        Call<ResponseBody> call = NetworkService.getAllPosts();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                try {
                    String rawRespons = response.body().string();
                    System.out.println(rawRespons);
                    getArrayListFromJsonarray(rawRespons);
                    setAppStatusInfo("ref1234567890");
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getArrayListFromJsonarray(String jsonArray){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PostsModel>>(){}.getType();
        List<PostsModel> myModelList = gson.fromJson(jsonArray, listType);
        for (PostsModel model : myModelList)
            System.out.println("MODEL : "+model.getId());
    }


    private void setAppStatusInfo(String registeredId){
        AppPreference appPreference = ((KidApplication)getApplication()).mAppPreference;
        appPreference.setAppRefId(registeredId);
    }



    private void executeUserPermissionTree(){
        AppDialog appDialog = new AppDialog();
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, android.Manifest.permission.READ_PHONE_STATE))
            permissionsNeeded.add("Read Phone State");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);

                appDialog.showErrorDialog(SplashActivity.this, "Permission", message, new AppDialog.DialogListener() {
                    @Override
                    public void OnPositivePress(Object val) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                        }
                    }

                    @Override
                    public void OnNegativePress() {
                        finish();
                    }
                });

                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
            return;
        }


    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
            {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(android.Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    initializeSplash();

                } else {
                    // Permission Denied
                    finish();

                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
