package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController;

import android.os.Bundle;
import android.view.*;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.databinding.FragmentCompletedStatsTabContainerBinding;


public class CompletedStatsTabContainer extends Fragment {
    private FragmentCompletedStatsTabContainerBinding binding;

    public CompletedStatsTabContainer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCompletedStatsTabContainerBinding.inflate(inflater, container,false);

        // Configure table layout with view pager
        PageAdapterCompletedStats sectionsPagerAdapter = new PageAdapterCompletedStats(getContext(),getChildFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}