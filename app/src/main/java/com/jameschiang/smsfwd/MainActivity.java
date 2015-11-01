package com.jameschiang.smsfwd;


import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
//import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    public static final String ACTION_BG_SRV = "com.jameschiang.smsfwd.SmsFwdBgSrv";
    public static final String ACTION_BG_RCV = "com.jameschiang.smsfwd.SmsFwdBgRcv";

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        final int pagerMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pagerMargin);

        tabs.setViewPager(pager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                sendBroadcast(new Intent(ACTION_BG_RCV));
            }
        });

        Intent intentStartBgSrv = new Intent();
        intentStartBgSrv.setAction(ACTION_BG_SRV);
        startService(intentStartBgSrv);
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

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private final int[] titles = {R.string.title_sms, R.string.title_weixin, R.string.title_weibo, R.string.title_email};
        @Override
        public Fragment getItem(int position) {
            return SuperAwesomeCardFragment.newInstance(position);
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return super.isViewFromObject(view, object);
        }

        @Override
        public Parcelable saveState() {
            return super.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            super.registerDataSetObserver(observer);
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            super.unregisterDataSetObserver(observer);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }
    }
}
