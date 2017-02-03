package com.meatshop.model;

import com.meatshop.BuildConfig;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TwitterAPI {

    @FormUrlEncoded
    @POST(BuildConfig.TWITTER_AUTH_EP)
    Call<ResponseBody> authorization(@Field(BuildConfig.GRANT_TYPE) String yourField);

    @GET(BuildConfig.TWITTER_SEARCH_EP)
    Call<ResponseBody> search(@Query(BuildConfig.QUERY_PARAM) String searchItem);

}
