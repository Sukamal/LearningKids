package com.suku.learningkids.purchase;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.android.vending.billing.IInAppBillingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SukamalD on 13-02-2018.
 */

public class InAppPurchaseManager implements PurchasesUpdatedListener{

    private static final String TAG = "BillingManager";
    private Activity mActivity;
    public BillingClient mBillingClient;
    public IInAppBillingService mService;
    public ServiceConnection mServiceConn;
    private PurchaseListListner listner;

    public interface PurchaseListListner{
        public void onPurchaseList(Object items);
    }

    public void setListner(PurchaseListListner listner){
        this.listner = listner;
    }


    public InAppPurchaseManager(Activity activity){
        this.mActivity = activity;
        connectToPayServer();
    }

    private void connectToPayServer(){
        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(int responseCode) {
                if (responseCode == BillingClient.BillingResponse.OK) {
                    // The billing client is ready. You can query purchases here.
                    handlePurchase(null);
                    Log.i(TAG, "onBillingSetupFinished() response: " + responseCode);
                } else {
                    Log.w(TAG, "onBillingSetupFinished() error code: " + responseCode);
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                Log.w(TAG, "onBillingServiceDisconnected()");
            }
        });
    }

    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
            for (Purchase purchase : purchases) {

            }
        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
            // Handle an error caused by a user canceling the purchase flow.
        } else {
            // Handle any other error codes.
        }
    }

    public void startPurchaseFlow(String skuId, String billingType) {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setType(billingType).setSku(skuId).build();
        mBillingClient.launchBillingFlow(mActivity, billingFlowParams);
    }


    public void handlePurchase(Purchase purchase){
        Toast.makeText(mActivity,"OPEN PERCHASE", Toast.LENGTH_SHORT).show();

        List<String> skuList = new ArrayList<>();
        skuList.add("leaerningbubu");
        skuList.add("premium");

        SkuDetailsParams params = SkuDetailsParams.newBuilder()
                .setSkusList(skuList)
                .setType(BillingClient.SkuType.INAPP).build();

        mBillingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                    List<SkuDetailData> inList = new ArrayList<>();
                    for (SkuDetails details : skuDetailsList) {
                        Log.i(TAG, "Found sku: " + details);
                        inList.add(new SkuDetailData(details.getSku(), details.getTitle(), details.getPrice(),
                                details.getDescription(), details.getType()));
                    }

                    if (inList.size() == 0) {
                    } else {
                        listner.onPurchaseList(inList);
                    }
                }
            }
        });

    }

}
