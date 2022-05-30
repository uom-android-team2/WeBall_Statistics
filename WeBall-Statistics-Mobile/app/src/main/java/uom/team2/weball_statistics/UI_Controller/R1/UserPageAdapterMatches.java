package uom.team2.weball_statistics.UI_Controller.R1;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import uom.team2.weball_statistics.R;

import android.content.Context;

import androidx.annotation.*;
import androidx.fragment.app.*;

import uom.team2.weball_statistics.R;

public class UserPageAdapterMatches extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.liveMatches,R.string.previousMatches};
    private final Context mContext;


    public UserPageAdapterMatches(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment
        int index = position + 1;
        switch (index)
        {
            case 1:
                return new LiveMatches();
            case 2:
                return new PreviousMatches(); // previous()
            default:
                return new LiveMatches();
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // show all pages in menu
        return TAB_TITLES.length;
    }
}

