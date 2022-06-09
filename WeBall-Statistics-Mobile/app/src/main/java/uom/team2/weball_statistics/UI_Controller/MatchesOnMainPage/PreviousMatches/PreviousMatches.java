package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.PreviousMatches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.LiveMatches.LiveMatches;
import uom.team2.weball_statistics.databinding.FragmentLiveMatchesBinding;
import uom.team2.weball_statistics.databinding.FragmentPreviousMatchesBinding;

public class PreviousMatches extends Fragment {

    private FragmentPreviousMatchesBinding binding;

    public PreviousMatches() { }

    public static PreviousMatches getInstance(){
        return new PreviousMatches();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPreviousMatchesBinding.inflate(inflater, container, false);

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
