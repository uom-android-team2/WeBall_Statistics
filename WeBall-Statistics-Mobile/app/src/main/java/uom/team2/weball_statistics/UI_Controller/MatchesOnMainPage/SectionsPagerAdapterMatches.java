package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Comments.LiveGameComments;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveGameProgress;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveGameStatistics;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.LiveMatches.LiveMatches;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.PreviousMatches.PreviousMatches;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.UpcomingMatches.UpcomingMatches;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class SectionsPagerAdapterMatches extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.previous_matches_tab_text, R.string.live_matches_tab_text, R.string.upcoming_matches_tab_text};
    private final Context mContext;

    public SectionsPagerAdapterMatches(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        int index = position + 1;
        switch (index) {
            case 1:
                return PreviousMatches.getInstance();
            case 3:
                return UpcomingMatches.getInstance();
            default:
                return LiveMatches.getInstance();
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
