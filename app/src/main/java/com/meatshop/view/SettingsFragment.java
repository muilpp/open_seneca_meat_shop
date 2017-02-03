package com.meatshop.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.meatshop.R;
import com.meatshop.model.BaseApplication;

public class SettingsFragment extends Fragment {
    private final static String TAG = SettingsFragment.class.getName();
    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_settings, container, false);

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView) {
        Spinner languageSpinner = (Spinner) rootView.findViewById(R.id.language_spinner);
        //prevent firing the first time
        languageSpinner.setSelection(0, false);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                //first option is -- select --, do nothing if no language gets selected
                if (!item.startsWith("--")) {
                    persistLocale(item.substring(0,2).toLowerCase());

                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void persistLocale(String lang) {
        BaseApplication.setLocale(lang);
    }
}
