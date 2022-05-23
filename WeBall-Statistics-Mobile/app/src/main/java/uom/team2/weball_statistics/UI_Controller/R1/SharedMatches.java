package uom.team2.weball_statistics.UI_Controller.R1;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.*;


import com.google.android.material.tabs.TabLayout;

import uom.team2.weball_statistics.databinding.FragmentSharedMatchesBinding;

public class SharedMatches extends Fragment {

    private FragmentSharedMatchesBinding binding;


    public SharedMatches() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SharedMatches newInstance(String param1, String param2) {
        SharedMatches fragment = new SharedMatches();
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
        binding = FragmentSharedMatchesBinding.inflate(inflater,container,false);
        PageAdapterMatches sectionsPagesAdapter = new PageAdapterMatches(getContext(), getChildFragmentManager());
        ViewPager viewPager =binding.viewPager;
        viewPager.setAdapter(sectionsPagesAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        return binding.getRoot();

    }
}