// IInAppBillingService.aidl
package com.suku.learningkids;

// Declare any non-default types here with import statements

interface IInAppBillingService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            int isBillingSupported(int apiVersion, String packageName, String type);

            Bundle getSkuDetails(int apiVersion, String packageName, String type, in Bundle skusBundle);

             Bundle getBuyIntent(int apiVersion, String packageName, String sku, String type,
                    String developerPayload);

                     Bundle getPurchases(int apiVersion, String packageName, String type, String continuationToken);

                     int consumePurchase(int apiVersion, String packageName, String purchaseToken);

                     int stub(int apiVersion, String packageName, String type);

                     Bundle getBuyIntentToReplaceSkus(int apiVersion, String packageName,
                             in List<String> oldSkus, String newSku, String type, String developerPayload);

                             Bundle getBuyIntentExtraParams(int apiVersion, String packageName, String sku,
                                     String type, String developerPayload, in Bundle extraParams);

                                     Bundle getPurchaseHistory(int apiVersion, String packageName, String type,
                                             String continuationToken, in Bundle extraParams);

                                             int isBillingSupportedExtraParams(int apiVersion, String packageName, String type,
                                                     in Bundle extraParams);
}
