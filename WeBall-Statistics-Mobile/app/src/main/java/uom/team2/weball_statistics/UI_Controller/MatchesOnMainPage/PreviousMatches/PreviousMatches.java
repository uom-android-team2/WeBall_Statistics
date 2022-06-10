package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.PreviousMatches;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.LiveMatches.LiveMatches;
import uom.team2.weball_statistics.databinding.FragmentLiveMatchesBinding;
import uom.team2.weball_statistics.databinding.FragmentPreviousMatchesBinding;
import uom.team2.weball_statistics.databinding.MatchesPreviousLayoutBinding;

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

        MatchesPreviousLayoutBinding matchesPreviousLayoutBinding = binding.includeTest;
        ImageButton buttonArrow = (ImageButton) matchesPreviousLayoutBinding.imageButtonDropdown;
        ConstraintLayout constraintLayoutExpandedView = (ConstraintLayout) matchesPreviousLayoutBinding.expandedViewLayout;
        CardView cardViewCompletedMatch = (CardView) matchesPreviousLayoutBinding.cardViewCompletedMatch;

        buttonArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (constraintLayoutExpandedView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardViewCompletedMatch, new AutoTransition());
                    constraintLayoutExpandedView.setVisibility(View.VISIBLE);
                    buttonArrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(cardViewCompletedMatch, new AutoTransition());
                    constraintLayoutExpandedView.setVisibility(View.GONE);
                    buttonArrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });


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
