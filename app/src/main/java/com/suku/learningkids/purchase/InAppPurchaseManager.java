package com.suku.learningkids.purchase;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.vending.billing.IInAppBillingService;

import java.util.List;

/**
 * Created by SukamalD on 13-02-2018.
 */

public class InAppPurchaseManager {

    public BillingClient mBillingClient;
    private Activity activity;

    public IInAppBillingService mService;
    public ServiceConnection mServiceConn;

    public InAppPurchaseManager(Activity activity){
        this.activity = activity;
        connectToPayServer();
        createServiceConnection();
    }

    public void connectToPayServer(){
        mBillingClient = BillingClient.newBuilder(activity).setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {

            }
        }).build();

        mBillingClient.startConnection(new BillingClientStateListener() {

            @Override
            public void onBillingSetupFinished(int responseCode) {
                if (responseCode == BillingClient.BillingResponse.OK) {
                    // The billing client is ready. You can query purchases here.
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
    }

    public void createServiceConnection(){

        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IInAppBillingService.Stub.asInterface(service);
                Toast.makeText(activity,"Service Connected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }

        };

    }

    public void unregisterService(){
        if (mService != null) {
            activity.unbindService(mServiceConn);
        }
    }



}
