package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentMatchesTabContainerBinding;
import uom.team2.weball_statistics.utils.Utils;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class MatchesTabContainer extends Fragment {

    private FragmentMatchesTabContainerBinding binding;

    public MatchesTabContainer() {
        // Required empty public constructor
    }

    private void tabBackgroundColorHandler(TabLayout tabs) {
        int backgroundSelectedColor = Utils.getColor(getContext(), R.color.red_buttons);
        int backgroundDefaultColor = Utils.getColor(getContext(), R.color.grey);

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
        binding = FragmentMatchesTabContainerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        SectionsPagerAdapterMatches sectionsPagerAdapter = new SectionsPagerAdapterMatches(getContext(), getChildFragmentManager(), bundle);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        tabBackgroundColorHandler(tabs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
