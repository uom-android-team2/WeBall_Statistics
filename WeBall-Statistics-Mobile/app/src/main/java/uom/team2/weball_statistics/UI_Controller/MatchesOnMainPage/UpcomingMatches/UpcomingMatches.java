package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.UpcomingMatches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.databinding.FragmentUpcomingMatchesBinding;

public class UpcomingMatches extends Fragment {

    private FragmentUpcomingMatchesBinding binding;

    public UpcomingMatches() { }

    public static UpcomingMatches getInstance(){
        return new UpcomingMatches();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpcomingMatchesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
