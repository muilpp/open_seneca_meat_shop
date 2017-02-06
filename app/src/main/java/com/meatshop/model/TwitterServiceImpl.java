package com.meatshop.model;

import android.util.Log;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.meatshop.BuildConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwitterServiceImpl implements TwitterService {
    private final static String TAG = TwitterServiceImpl.class.getName();
    private String twitterBearer;
    private ViewFlipper viewFlipper;
    private List<TwitterStatus> statusList;

    public TwitterServiceImpl (ViewFlipper viewFlipper, List<TwitterStatus> statusList) {
        this.viewFlipper = viewFlipper;
        this.statusList = statusList;
    }

    @Override
    public void authorize() {
        TwitterAPI twitterAPI = TwitterRetrofit.getAuthorizationInstance();
        twitterAPI.authorization(BuildConfig.CLIENT_CREDENTIALS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        TwitterToken token = new Gson().fromJson(response.body().string(), TwitterToken.class);
                        twitterBearer = token.getAccess_token();
                        search();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                } else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    @Override
    public void search() {
        //statusList is filled from the saved instance, no need to call the API
        if (!statusList.isEmpty()) {
            ViewFlipperHelper.addTwitterStatus(viewFlipper, statusList);
            return;
        }

        if (twitterBearer == null) {
            authorize();
            return;
        }

        TwitterAPI twitterAPI = TwitterRetrofit.getRequestInstance(twitterBearer);
        twitterAPI.search("\"meat healthy\"").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        TwitterStatusDTO twitterStatusDTO = new Gson().fromJson(response.body().string(), TwitterStatusDTO.class);
                        statusList.addAll(twitterStatusDTO.getStatusList());
                        ViewFlipperHelper.addTwitterStatus(viewFlipper, twitterStatusDTO.getStatusList());
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                } else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }
}