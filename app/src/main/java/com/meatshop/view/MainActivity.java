package com.meatshop.view;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.meatshop.R;
import com.meatshop.model.BaseApplication;
import com.meatshop.model.LocaleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view) NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initNavDrawer();

        //add home fragment only the first time the activity is created
        if (savedInstanceState == null)
            initViews();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void attachBaseContext(Context newBase) {
        String locale = BaseApplication.getLocale();

        if (locale == null || locale.isEmpty()) {
            super.attachBaseContext(newBase);
        } else {
            super.attachBaseContext(LocaleHelper.wrap(newBase, locale));
        }
    }

    private void initViews() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, homeFragment, getString(R.string.menu_home))
                .commit();
    }

    private void initNavDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                final FragmentManager fragmentManager = getFragmentManager();
                switch (id) {
                    case R.id.home:
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_content, homeFragment, getString(R.string.menu_home))
                                .addToBackStack(getString(R.string.menu_home))
                                .commit();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.our_shops:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(MainActivity.this, ShopMapsActivity.class));
                        break;

                    case R.id.tweets:
                        TweetsFragment tweetsFragment = new TweetsFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_content, tweetsFragment, getString(R.string.menu_tweets))
                                .addToBackStack(getString(R.string.menu_tweets))
                                .commit();
                        mDrawerLayout.closeDrawers();
                        break;

                    case R.id.settings:
                        SettingsFragment settingsFragment = new SettingsFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_content, settingsFragment, getString(R.string.menu_settings))
                                .addToBackStack(getString(R.string.menu_settings))
                                .commit();
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }
}