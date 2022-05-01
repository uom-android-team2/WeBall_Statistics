package uom.team2.weball_statistics.UI_Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentTabContainerBinding;

/*
 * @author Leonard Pepa ics20033
 */
public class TabContainer extends Fragment {

    private FragmentTabContainerBinding binding;

    public TabContainer() {
        // Required empty public constructor
    }

    private void tabBackgroundColorHandler(TabLayout tabs){
        int backgroundSelectedColor = getResources().getColor(R.color.red_buttons);
        int backgroundDefaultColor = getResources().getColor(R.color.background_player_live_stats_button_color);

        tabs.getTabAt(tabs.getSelectedTabPosition()).view.setBackgroundColor(backgroundSelectedColor);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setBackgroundColor(backgroundSelectedColor);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setBackgroundColor(backgroundDefaultColor);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTabContainerBinding.inflate(inflater, container, false);

        // configure tablayout with view pager
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // handles the background color of the selected tab
        tabBackgroundColorHandler(tabs);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}