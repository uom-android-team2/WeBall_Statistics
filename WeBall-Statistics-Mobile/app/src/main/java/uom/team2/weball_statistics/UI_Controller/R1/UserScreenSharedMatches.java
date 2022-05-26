package uom.team2.weball_statistics.UI_Controller.R1;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.*;


import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.databinding.FragmentSharedMatchesBinding;
import uom.team2.weball_statistics.databinding.FragmentUserScreenSharedMatchesBinding;

public class UserScreenSharedMatches extends Fragment {

    private FragmentUserScreenSharedMatchesBinding binding;


    public UserScreenSharedMatches() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UserScreenSharedMatches newInstance(String param1, String param2) {
        UserScreenSharedMatches fragment = new UserScreenSharedMatches();
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
        binding = FragmentUserScreenSharedMatchesBinding.inflate(inflater,container,false);
        UserPageAdapterMatches sectionsPagesAdapter = new UserPageAdapterMatches(getContext(), getChildFragmentManager());
        ViewPager viewPager = binding.viewPager2;  //viewPager2 είναι το id του viewPager του fragment_user_screen_matches.xml
        viewPager.setAdapter(sectionsPagesAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        return binding.getRoot();

    }
}