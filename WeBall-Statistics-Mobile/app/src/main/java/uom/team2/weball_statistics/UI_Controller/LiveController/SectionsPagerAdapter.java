package uom.team2.weball_statistics.UI_Controller.LiveController;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Comments.LiveGameComments;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveGameProgress;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveGameStatistics;

/*
 * @author Leonard Pepa ics20033
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.live_game_tab_text, R.string.live_comments_tab_text, R.string.live_statistics_tab_text};
    private final Context mContext;
    private Bundle bundle;
    public SectionsPagerAdapter(Context context, FragmentManager fm, Bundle bundle) {
        super(fm);
        mContext = context;
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        int index = position + 1;
        switch (index) {
            case 1:
                return LiveGameProgress.getInstance(bundle);
            case 3:
                return LiveGameStatistics.getInstance(bundle);
            default:
                return LiveGameComments.getInstance(bundle);
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