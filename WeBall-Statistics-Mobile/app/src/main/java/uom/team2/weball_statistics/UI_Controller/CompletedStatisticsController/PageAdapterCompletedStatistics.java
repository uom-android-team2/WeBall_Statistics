package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.TeamStats.TeamStatsFragment;


/*
 *  @author Evmorfia Elpida Dasyra ics20006
 *  @author Aravella Lousta ics20032
 */
public class PageAdapterCompletedStatistics extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.playerStatsTab, R.string.teamStatsTab};

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
        switch (index) {
            case 1:
                return LeadersStats.newInstance(); //first tab LeadersStats
            case 2:
                return TeamStatsFragment.newInstance(); //second tab TeamStats
            default:
                return LeadersStats.newInstance();
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
