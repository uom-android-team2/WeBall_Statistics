package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.json.JSONException;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Match;

import uom.team2.weball_statistics.Model.Team;

import uom.team2.weball_statistics.databinding.CompletedMatchStatsBinding;

public class CompletedMatchStats extends Fragment {

    private CompletedMatchStatsUIController completedMatchStatsUIController = CompletedMatchStatsUIController.getInstance();
    private CompletedMatchStatsBinding binding;


    public CompletedMatchStats() {

    }


    public static uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats.CompletedMatchStats getInstance() {
        return new uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats.CompletedMatchStats();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = CompletedMatchStatsBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();


        Match match = (Match) bundle.getSerializable("match");
        Team teamLandlord = (Team) bundle.getSerializable("teamLandlord");
        Team teamGuest = (Team) bundle.getSerializable("teamGuest");

        //Change call to method
        try {
            completedMatchStatsUIController.fillMatchHeaderInformation(this,match,teamLandlord,teamGuest);
            completedMatchStatsUIController.fillCompleteMatchTeamPlayerStats(this,match,teamLandlord,teamGuest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public CompletedMatchStatsBinding getBinding() {
        return binding;
    }
}