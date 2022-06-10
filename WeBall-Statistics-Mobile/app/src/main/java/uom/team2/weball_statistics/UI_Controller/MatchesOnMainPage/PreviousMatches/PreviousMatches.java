package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.PreviousMatches;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentPreviousMatchesBinding;

public class PreviousMatches extends Fragment {

    private FragmentPreviousMatchesBinding fragmentPreviousMatchesBinding;

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
        fragmentPreviousMatchesBinding = FragmentPreviousMatchesBinding.inflate(inflater, container, false);

        return fragmentPreviousMatchesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Create dynamic matches and add event Listener to button of each match
        for (int i = 0; i < 6; i++) {

            View viewMatch = (View) getLayoutInflater().inflate(R.layout.matches_previous_layout, null);

            viewMatch.findViewById(R.id.imageButtonDropdown).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConstraintLayout constraintLayoutExpandedView = viewMatch.findViewById(R.id.expandedViewLayout);
                    CardView cardViewCompletedMatch = viewMatch.findViewById(R.id.cardViewCompletedMatch);

                    if (constraintLayoutExpandedView.getVisibility() == View.GONE) {
                        //Maybe the selected import for TransitionManager will cause problems to olders sdk
                        TransitionManager.beginDelayedTransition(cardViewCompletedMatch, new AutoTransition());
                        constraintLayoutExpandedView.setVisibility(View.VISIBLE);
                        viewMatch.findViewById(R.id.imageButtonDropdown).setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    } else {
                        TransitionManager.beginDelayedTransition(cardViewCompletedMatch, new AutoTransition());
                        constraintLayoutExpandedView.setVisibility(View.GONE);
                        viewMatch.findViewById(R.id.imageButtonDropdown).setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    }
                }
            });

            //Add view to the container
            fragmentPreviousMatchesBinding.matchesLayoutContainer.addView(viewMatch);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPreviousMatchesBinding = null;
    }
}
