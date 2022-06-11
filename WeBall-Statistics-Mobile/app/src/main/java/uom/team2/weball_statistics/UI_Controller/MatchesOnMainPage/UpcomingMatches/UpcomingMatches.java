package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.UpcomingMatches;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Pair;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.PlayerService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.LiveMatches.LiveMatches;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.Service.MatchesOnMainPageService;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentUpcomingMatchesBinding;

public class UpcomingMatches extends Fragment {
    private final HashMap<Integer, Pair<Team>> hashMap = new HashMap<>();
    private final HashMap<Integer, Match> mapOfMatches = new HashMap<>();
    private FragmentUpcomingMatchesBinding binding;
    private boolean isAdmin = false;

    public UpcomingMatches() { }

    public static UpcomingMatches getInstance(Bundle bundle){
        UpcomingMatches upcomingMatches = new UpcomingMatches();
        upcomingMatches.setArguments(bundle);
        return upcomingMatches;
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
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        isAdmin = bundle.getBoolean("isAdmin");
    }



    @Override
    public void onStart() {
        super.onStart();

        MatchesOnMainPageService matchesOnMainPageService = new MatchesOnMainPageService();
        matchesOnMainPageService.fetchUpcomingMatches(new CallbackListener<ArrayList<Match>>() {
            @Override
            public void callback(ArrayList<Match> returnedObject) {
                createMatchLayout(returnedObject);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void createMatchLayout(ArrayList<Match> liveMatches) {
        //Create dynamic matches and add event Listener to button of each match

        TeamService teamService = new TeamService();


        for (int i = 0; i < liveMatches.size(); i++) {
            mapOfMatches.put(liveMatches.get(i).getId(), liveMatches.get(i));
            Pair<Team> pair = new Pair<Team>();
            View viewMatch = getLayoutInflater().inflate(R.layout.matches_upcoming_layout, null);
            if (isAdmin) {
                viewMatch.findViewById(R.id.imageButtonEditMatch).setVisibility(View.VISIBLE);
            } else {
                viewMatch.findViewById(R.id.imageButtonEditMatch).setVisibility(View.INVISIBLE);
            }
            teamService.findTeamById(liveMatches.get(i).getTeamLandlord_id(), new CallbackListener<Team>() {
                @Override
                public void callback(Team returnedObject) {
                    pair.teamLandlord = returnedObject;
                    View team1 = viewMatch.findViewById(R.id.team1);
                    try {
                        UIHandler.updateTeamImageInMatch(UpcomingMatches.this, returnedObject, team1);
                        fillPlayers(returnedObject, viewMatch, true);
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
                    pair.teamGuest = returnedObject;
                    View team2 = viewMatch.findViewById(R.id.team2);
                    try {
                        UIHandler.updateTeamImageInMatch(UpcomingMatches.this, returnedObject, team2);
                        fillPlayers(returnedObject, viewMatch, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            onclickView(viewMatch, liveMatches.get(i).getId());
            hashMap.put(liveMatches.get(i).getId(), pair);
            UpcomingMatches.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (binding != null){
                        binding.matchesLayoutContainer.addView(viewMatch);
                    }
                }
            });
            navigate(viewMatch, liveMatches.get(i).getId());
        }
    }

    public void navigate(View viewMatch, int matchid) {
        ImageButton imageButton = viewMatch.findViewById(R.id.imageButtonEditMatch);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("match", mapOfMatches.get(matchid));
                bundle.putSerializable("teamLandlord", hashMap.get(matchid).teamLandlord);
                bundle.putSerializable("teamGuest", hashMap.get(matchid).teamGuest);
                NavHostFragment.findNavController(UpcomingMatches.this)
                        .navigate(R.id.action_matchesTabContainer_to_adminsView, bundle);
            }
        });
    }


    public void fillPlayers(Team team, View viewMatch, boolean home) {
        PlayerService playerService = new PlayerService();

        playerService.findAllPlayersByTeamName(team.getTeamName(), new CallbackListener<ArrayList<Player>>() {
            @Override
            public void callback(ArrayList<Player> players) {
                team.setTeamPlayers(players);
                for (int i = 0; i < players.size(); i++) {
                    View playerView = getLayoutInflater().inflate(R.layout.player_layout, null);
                    View container = viewMatch.findViewById(R.id.matchPlayersInfo);
                    int layoutId = home ? R.id.homeTeamStartingPlayersVertical : R.id.awayTeamStartingPlayersVertical;
                    LinearLayout linearLayout = container.findViewById(layoutId);

                    int finalI = i;
                    UpcomingMatches.this.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = playerView.findViewById(R.id.textViewPlayerName);
                            ImageView imageView = playerView.findViewById(R.id.playerImageView);
                            Picasso.get().load(Config.PLAYER_IMAGES_RESOURCES + players.get(finalI).getImagePath())
                                    .centerCrop()
                                    .resize(70, 70)
                                    .into(imageView);
                            textView.setText(players.get(finalI).getName().toUpperCase(Locale.ROOT).charAt(0) + ". " + players.get(finalI).getSurname());
                            linearLayout.addView(playerView);
                        }
                    });
                }
            }
        });
    }

    public void onclickView(View viewMatch, int matchId) {
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
