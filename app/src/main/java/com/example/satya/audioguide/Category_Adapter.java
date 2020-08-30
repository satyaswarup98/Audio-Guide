package com.example.satya.audioguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Category_Adapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "SCAN", "RECORD", "DOWNLOAD" };
    private Context context;


    public Category_Adapter(FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ScanFragment();
        } else if (position == 1){
            return new RecordingFragment();
        } else {
            return new DownloadFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


}
