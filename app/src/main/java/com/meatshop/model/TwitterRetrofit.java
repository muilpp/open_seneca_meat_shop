package com.meatshop.model;

import android.util.Base64;
import android.util.Log;
import com.meatshop.BuildConfig;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitterRetrofit {
    private static TwitterAPI twitterAuthAPI;
    private static TwitterAPI twitterRequestAPI;
    private final static String TAG = TwitterRetrofit.class.getName();

    public static TwitterAPI getAuthorizationInstance() {
        if (twitterAuthAPI == null) {

            final String base64Credentials = Base64.encodeToString(BuildConfig.TWITTER_API_CREDENTIALS.getBytes(), Base64.NO_WRAP);

            OkHttpClient okClientAuthorizationInterceptor = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request original = chain.request();
                                    Request request = original.newBuilder()
                                            .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                            .header("Authorization", "Basic " + base64Credentials)
                                            .method(original.method(), original.body())
                                            .build();

                                    Log.i(TAG, "Url -> " + request.url());
                                    return chain.proceed(request);
                                }
                            })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.TWITTER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okClientAuthorizationInterceptor)
                    .build();

            twitterAuthAPI = retrofit.create(TwitterAPI.class);
        }

        return twitterAuthAPI;
    }

    public static TwitterAPI getRequestInstance(final String twitterBearer) {
        if (twitterRequestAPI == null) {
            OkHttpClient okClientAuthorizationInterceptor = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request original = chain.request();
                                    Request request = original.newBuilder()
                                            .header("Authorization", "Bearer " + twitterBearer)
                                            .method(original.method(), original.body())
                                            .build();

                                    Log.i(TAG, "Url -> " + request.url());
                                    return chain.proceed(request);
                                }
                            })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.TWITTER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okClientAuthorizationInterceptor)
                    .build();

            twitterRequestAPI = retrofit.create(TwitterAPI.class);
        }

        return twitterRequestAPI;
    }
}