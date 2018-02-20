package com.suku.learningkids.purchase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.suku.learningkids.R;

public class PurchaseActivity extends AppCompatActivity {

    private InAppPurchaseManager inAppPurchaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        inAppPurchaseManager= new InAppPurchaseManager(this);


        findViewById(R.id.state_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                inAppPurchaseManager.

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
