package uom.team2.weball_statistics.UI_Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentHomeBinding;

public class Home extends Fragment {

    private FragmentHomeBinding binding;

    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.goToPage.setOnClickListener(e ->{
            NavHostFragment.findNavController(this).navigate(R.id.action_home_to_bestStarting5);
        });
        return binding.getRoot();
    }
}