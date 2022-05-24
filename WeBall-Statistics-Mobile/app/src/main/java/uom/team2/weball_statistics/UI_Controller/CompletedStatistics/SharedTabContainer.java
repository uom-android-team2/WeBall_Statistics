package uom.team2.weball_statistics.UI_Controller.CompletedStatistics;

import android.os.Bundle;
import android.view.*;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.databinding.FragmentSharedTabContainerBinding;

/*
 *  @author Evmorfia Elpida Dasyra ics20006
 */
public class SharedTabContainer extends Fragment {
    private FragmentSharedTabContainerBinding binding;

    public SharedTabContainer() {
        // Required empty public constructor
    }


    public static SharedTabContainer newInstance(String param1, String param2) {
        SharedTabContainer fragment = new SharedTabContainer();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSharedTabContainerBinding.inflate(inflater, container,false);
        PageAdapterCompletedStatistics sectionsPagerAdapter = new PageAdapterCompletedStatistics(getContext(),getChildFragmentManager());

        // configure table layout with view pager
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        return binding.getRoot();
    }
}