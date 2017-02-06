package com.meatshop.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.meatshop.BuildConfig;
import com.meatshop.R;
import com.meatshop.model.TwitterService;
import com.meatshop.model.TwitterServiceImpl;
import com.meatshop.model.TwitterStatus;
import com.meatshop.model.ViewFlipperHelper;

import java.util.ArrayList;

public class TweetsFragment extends Fragment {
    private final static String TAG = TweetsFragment.class.getName();
    private ArrayList<TwitterStatus> statusList;
    private ViewFlipper viewFlipper;

    public TweetsFragment() {
        statusList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tweets, container, false);

        int viewFlipperIndex = 0;
        if (savedInstanceState != null) {
            statusList = savedInstanceState.getParcelableArrayList(BuildConfig.TWEET_STATUS_LIST);
            viewFlipperIndex = savedInstanceState.getInt(BuildConfig.VIEW_FLIPPER_INDEX);
        }

        initViews(rootView, viewFlipperIndex);

        return rootView;
    }

    private void initViews(View rootView, int viewFlipperIndex) {
        viewFlipper = (ViewFlipper) rootView.findViewById(R.id.view_flipper);
        ViewFlipperHelper.init(viewFlipper, rootView.findViewById(R.id.play), rootView.findViewById(R.id.stop));

        TwitterService twitterService = new TwitterServiceImpl(viewFlipper, statusList);
        twitterService.search();

        viewFlipper.setDisplayedChild(viewFlipperIndex);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(BuildConfig.TWEET_STATUS_LIST, statusList);

        if (viewFlipper != null)
            outState.putInt(BuildConfig.VIEW_FLIPPER_INDEX, viewFlipper.getDisplayedChild());
    }
}