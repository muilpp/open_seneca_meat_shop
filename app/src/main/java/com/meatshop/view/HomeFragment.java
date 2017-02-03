package com.meatshop.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.meatshop.R;
import com.meatshop.model.IntroItemAdapter;
import com.meatshop.model.StaticData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.home_scroll_view) ScrollView homeScrollView;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.item_recyclerview);
        //the recycler is inside a scroll view
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new IntroItemAdapter(StaticData.getIntroItemList(getActivity()), getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        homeScrollView.smoothScrollTo(0,0);
    }
}
