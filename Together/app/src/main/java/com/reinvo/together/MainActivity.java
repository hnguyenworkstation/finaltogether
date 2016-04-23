package com.reinvo.together;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import AppConnector.MainFragments.AboutMeFragment;
import AppConnector.MainFragments.ActivityFragment;
import AppConnector.MainFragments.MapFragment;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "LsbF5INqyStcxQVrC7Xxx8L13";
    private static final String TWITTER_SECRET = "M1UGLoV6I1EzWQlvDIIWf2So6Jbig3PzjJ6UHwUkZYL8YWFJ2F";

    private TabLayout tabLayout;

    private int[] tabIcons = {
           R.drawable.ic_tab_home,
           R.drawable.ic_tab_map,
           R.drawable.ic_tab_person
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        // Get the floating action button
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.main_fab);
        // TODO: 4/7/2016 : There is something wrong with the StartActivity Line...
        assert myFab != null;
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: 4/7/2016: This is not a good practice -- Implement to test FAB action
                // Try to Open a dialog
                startActivity(new Intent(MainActivity.this, QuickAddActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActivityFragment(), "Home");
        adapter.addFragment(new MapFragment(), "Connects");
        adapter.addFragment(new AboutMeFragment(), "About Me");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
