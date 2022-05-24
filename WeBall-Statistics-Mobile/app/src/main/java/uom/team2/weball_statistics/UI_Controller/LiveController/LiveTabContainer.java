package uom.team2.weball_statistics.UI_Controller.LiveController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLiveTabContainerBinding;
import uom.team2.weball_statistics.utils.Utils;

/*
 * @author Leonard Pepa ics20033
 */
public class LiveTabContainer extends Fragment {

    private FragmentLiveTabContainerBinding binding;

    public LiveTabContainer() {
        // Required empty public constructor
    }

    private void tabBackgroundColorHandler(TabLayout tabs){
        int backgroundSelectedColor = Utils.getColor(getContext(), R.color.red_buttons);
        int backgroundDefaultColor = Utils.getColor(getContext(), R.color.background_player_live_stats_button_color);

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
        binding = FragmentLiveTabContainerBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // configure tablayout with view pager
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // handles the background color of the selected tab
        tabBackgroundColorHandler(tabs);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}