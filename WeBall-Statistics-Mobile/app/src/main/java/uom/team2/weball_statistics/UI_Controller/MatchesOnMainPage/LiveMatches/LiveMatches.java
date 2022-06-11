package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.LiveMatches;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.PlayerService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.Service.MatchesOnMainPageService;
import uom.team2.weball_statistics.databinding.FragmentLiveMatchesBinding;

public class LiveMatches extends Fragment {

    private FragmentLiveMatchesBinding binding;

    public LiveMatches() {
    }

    public static LiveMatches getInstance() {
        return new LiveMatches();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveMatchesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        MatchesOnMainPageService matchesOnMainPageService = new MatchesOnMainPageService();

        matchesOnMainPageService.fetchLiveMatches(new CallbackListener<ArrayList<Match>>() {
            @Override
            public void callback(ArrayList<Match> returnedObject) {
                createMatchLayout(returnedObject);
            }
        });
    }


    private void createMatchLayout(ArrayList<Match> liveMatches) {
        //Create dynamic matches and add event Listener to button of each match

        TeamService teamService = new TeamService();


        for (int i = 0; i < liveMatches.size(); i++) {

            View viewMatch = getLayoutInflater().inflate(R.layout.matches_live_layout, null);

            teamService.findTeamById(liveMatches.get(i).getTeamLandlord_id(), new CallbackListener<Team>() {
                @Override
                public void callback(Team returnedObject) {
                    onclickView(viewMatch);
                    View team1 = viewMatch.findViewById(R.id.team1);
                    try {
                        UIHandler.updateTeamImageInMatch(LiveMatches.this, returnedObject, team1);
                        fillPlayers(returnedObject.getTeamName(), viewMatch, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            teamService.findTeamById(liveMatches.get(i).getTeamguest_id(), new CallbackListener<Team>() {
                @Override
                public void callback(Team returnedObject) {
                    View team2 = viewMatch.findViewById(R.id.team2);
                    try {
                        UIHandler.updateTeamImageInMatch(LiveMatches.this, returnedObject, team2);
                        fillPlayers(returnedObject.getTeamName(), viewMatch, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            binding.matchesLayoutContainer.addView(viewMatch);
        }
    }

    public void fillPlayers(String teamName, View viewMatch, boolean home) {
        PlayerService playerService = new PlayerService();

        playerService.findAllPlayersByTeamName(teamName, new CallbackListener<ArrayList<Player>>() {
            @Override
            public void callback(ArrayList<Player> players) {
                for (int i = 0; i < players.size(); i++) {
                    View playerView = getLayoutInflater().inflate(R.layout.player_layout, null);
                    View container = viewMatch.findViewById(R.id.matchPlayersInfo);
                    int layoutId = home ? R.id.homeTeamStartingPlayersVertical : R.id.awayTeamStartingPlayersVertical;
                    LinearLayout linearLayout = container.findViewById(layoutId);

                    int finalI = i;
                    LiveMatches.this.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = playerView.findViewById(R.id.textViewPlayerName);
                            textView.setText(players.get(finalI).getName().toUpperCase(Locale.ROOT).charAt(0) + ". " +players.get(finalI).getSurname());
                            linearLayout.addView(playerView);
                        }
                    });
                }
            }
        });
    }

    public void onclickView(View viewMatch) {
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

    }
}
