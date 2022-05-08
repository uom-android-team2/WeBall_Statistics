package uom.team2.weball_statistics.UI_Controller.CompletedStatistics;

import android.content.Context;

import androidx.annotation.*;
import androidx.fragment.app.*;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.*;


public class PageAdapterCompletedStatistics extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.playerStatsTab,R.string.teamStatsTab};
    private final Context mContext;

    public PageAdapterCompletedStatistics(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment
        int index = position + 1;
        switch (index)
        {
            case 1:
                return new PlayerStats();
            case 2:
                return new PlayerStats(); // ARAVELLAS TEAM STATS()
            default:
                return new PlayerStats();
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
