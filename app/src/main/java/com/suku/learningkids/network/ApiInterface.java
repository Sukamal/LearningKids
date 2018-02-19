package com.suku.learningkids.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
//import rx.Observable;

/**
 * Created by SukamalD on 27-12-2017.
 */

public interface ApiInterface {

//    @GET("activate_subscription.php")
//    Call<ResponseBody> getRawPostsData();


    @GET("posts")
    Call<ResponseBody> getRawPostsData();




}
