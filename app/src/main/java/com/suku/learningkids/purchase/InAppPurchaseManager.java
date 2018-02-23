package com.suku.learningkids.purchase;

import android.app.Activity;
import android.content.ServiceConnection;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.android.vending.billing.IInAppBillingService;
import com.suku.learningkids.util.UtilClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SukamalD on 13-02-2018.
 */

public class InAppPurchaseManager implements PurchasesUpdatedListener{

    private static final String BASE_64_ENCODED_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApkVBnuSf6pJCS/WbKJSGOONDxcbpiKiUtoCp2PQSZ9AD9x5IjAzZvK0YCPMXkVhlyTCsJ4tX/KxcMpQuoZfPoCvuxSZgJhKsHgOUQfsfV4qEF3xgmYwATVppvQvPOegXzKKC+H7pPZ8PoV8fILjjsxbIH3bcfffmwt/2EaKWCFHBLcyU9C+5MKAXf/XowEQc6xh9ucHkm/Hu6aQAd6JVzKfAfVb6T2FB6SbvjcQGEtu1piG36zb1x84ORq+0qW1vKoESKxZ3SLcttJqnJA0ziJyH31mdcP9M9mCKTWjs4Z9IEMKwvJeqgyBVjFXGflwpKQ6KrQEhBEhFc4OlVrk1BwIDAQAB";

    private static final String TAG = "BillingManager";
    private Activity mActivity;
    public BillingClient mBillingClient;
    public IInAppBillingService mService;
    public ServiceConnection mServiceConn;
    private PurchaseListListner listner;


    private boolean mIsServiceConnected;
    private int mBillingClientResponseCode = -1;
    private String mTokenToBeConsumed;



    public interface PurchaseListListner{
        public void onPurchaseList(Object items);
    }

    public void setListner(PurchaseListListner listner){
        this.listner = listner;
    }


    public InAppPurchaseManager(Activity activity){
        this.mActivity = activity;
        mBillingClient = BillingClient.newBuilder(mActivity).setListener(this).build();

        startServiceConnection(new Runnable() {
            @Override
            public void run() {
                queryPurchasesTest();
                querySkuDetailsAsync();
            }
        });
    }

    private void executeServiceRequest(Runnable runnable) {
        if (mIsServiceConnected) {
            runnable.run();
        } else {
            // If billing service was disconnected, we try to reconnect 1 time.
            // (feel free to introduce your retry policy here).
            startServiceConnection(runnable);
        }
    }

    public void startServiceConnection(final Runnable executeOnSuccess) {
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                Log.d(TAG, "Setup finished. Response code: " + billingResponseCode);

                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    mIsServiceConnected = true;
                    if (executeOnSuccess != null) {
                        executeOnSuccess.run();
                    }
                }
                mBillingClientResponseCode = billingResponseCode;
            }

            @Override
            public void onBillingServiceDisconnected() {
                mIsServiceConnected = false;
            }
        });
    }

    /**
     * Query purchases across various use cases and deliver the result in a formalized way through
     * a listener
     */
    public void queryPurchasesTest() {
        Runnable queryToExecute = new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
                List<Purchase> purchesedItems = purchasesResult.getPurchasesList();

                if(purchesedItems != null && purchesedItems.size() > 0){
                    for(Purchase purchase : purchesedItems){
                        if(purchase.getSku().equals("learningbubu1001")){
                            mTokenToBeConsumed = purchase.getPurchaseToken();
                            consumeAsync(mTokenToBeConsumed);
                        }
                    }
                }
            }
        };

        executeServiceRequest(queryToExecute);
    }

    public void queryPurchasesPrd() {
        Runnable queryToExecute = new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
                List<Purchase> purchesedItems = purchasesResult.getPurchasesList();

                if(purchesedItems != null && purchesedItems.size() > 0){
                    for(Purchase purchase : purchesedItems){
                        if(purchase.getSku().equals("learningbubu1001")){
                            UtilClass.setConfigAfterPurchage(mActivity);
                        }
                    }
                }
            }
        };

        executeServiceRequest(queryToExecute);
    }

    public void consumeAsync(final String purchaseToken) {

        // Generating Consume Response listener
        final ConsumeResponseListener onConsumeListener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(@BillingClient.BillingResponse int responseCode, String purchaseToken) {
                // If billing service was disconnected, we try to reconnect 1 time
                // (feel free to introduce your retry policy here).
            }
        };

        // Creating a runnable from the request to use it inside our connection retry policy below
        Runnable consumeRequest = new Runnable() {
            @Override
            public void run() {
                // Consume the purchase async
                mBillingClient.consumeAsync(purchaseToken, onConsumeListener);
            }
        };

        executeServiceRequest(consumeRequest);
    }


    public void querySkuDetailsAsync() {
        // Creating a runnable from the request to use it inside our connection retry policy below
        Runnable queryRequest = new Runnable() {
            @Override
            public void run() {


                List<String> skuList = new ArrayList<>();
                skuList.add("learningbubu1001");

                // Query the purchase async
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
                                mActivity.finish();
                            } else {
                                listner.onPurchaseList(inList);
                            }
                        }
                    }
                });
            }
        };
        executeServiceRequest(queryRequest);
    }





    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
            // Handle an error caused by a user canceling the purchase flow.
        } else {
            // Handle any other error codes.
        }
    }

    private void handlePurchase(Purchase purchase) {
        if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
            Log.i(TAG, "Got a purchase: " + purchase + "; but signature is bad. Skipping...");
            return;
        }
        Log.d(TAG, "Got a verified purchase: " + purchase);
        UtilClass.setConfigAfterPurchage(mActivity);
    }

    private boolean verifyValidSignature(String signedData, String signature) {
//        // Some sanity checks to see if the developer (that's you!) really followed the
//        // instructions to run this sample (don't put these checks on your app!)
//        if (BASE_64_ENCODED_PUBLIC_KEY.contains("CONSTRUCT_YOUR")) {
//            throw new RuntimeException("Please update your app's public key at: "
//                    + "BASE_64_ENCODED_PUBLIC_KEY");
//        }

        try {
            return Security.verifyPurchase(BASE_64_ENCODED_PUBLIC_KEY, signedData, signature);
        } catch (IOException e) {
            Log.e(TAG, "Got an exception trying to validate a purchase: " + e);
            return false;
        }
    }


    /**
     * Start a purchase or subscription replace flow
     */
    public void initiatePurchaseFlow(final String skuId, final @BillingClient.SkuType String billingType) {
        Runnable purchaseFlowRequest = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Launching in-app purchase flow. Replace old SKU? ");
                BillingFlowParams purchaseParams = BillingFlowParams.newBuilder()
                        .setSku(skuId).setType(billingType).build();
                mBillingClient.launchBillingFlow(mActivity, purchaseParams);
            }
        };

        executeServiceRequest(purchaseFlowRequest);
    }


    public void checkPurchaseStatus(){
        queryPurchasesPrd();
    }

}
