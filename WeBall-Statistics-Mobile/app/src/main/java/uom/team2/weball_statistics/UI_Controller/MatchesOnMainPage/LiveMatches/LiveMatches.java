package uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.LiveMatches;

import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Pair;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOLiveMatchService;
import uom.team2.weball_statistics.Service.PlayerService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.UIHandler;
import uom.team2.weball_statistics.UI_Controller.MatchesOnMainPage.Service.MatchesOnMainPageService;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentLiveMatchesBinding;

public class LiveMatches extends Fragment {

    private final HashMap<Integer, Pair<Team>> hashMap = new HashMap<>();
    private final HashMap<Integer, Match> mapOfMatches = new HashMap<>();
    private FragmentLiveMatchesBinding binding;
    private boolean isAdmin = false;
    private ProgressDialog progressDialog;

    public LiveMatches() {
    }

    public static LiveMatches getInstance(Bundle bundle) {
        LiveMatches liveMatches = new LiveMatches();
        liveMatches.setArguments(bundle);
        return liveMatches;
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
        Bundle bundle = getArguments();
        isAdmin = bundle.getBoolean("isAdmin");
        progressDialog = LayoutFactory.createNonCancelableProgressBar(getContext());
        if (binding != null) {
            binding.getRoot().setVisibility(View.INVISIBLE);
            progressDialog.show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (binding == null) {
            return;
        }

        MatchesOnMainPageService matchesOnMainPageService = new MatchesOnMainPageService();
        Thread thread = matchesOnMainPageService.fetchLiveMatches(new CallbackListener<ArrayList<Match>>() {
            @Override
            public void callback(ArrayList<Match> returnedObject) {
                createMatchLayout(returnedObject);
            }
        });

        Thread waitingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (LiveMatches.this.getActivity() != null && LiveMatches.this.isAdded()) {
                        LiveMatches.this.requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.getRoot().setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        });
                    }
                }
            }
        });
        waitingThread.start();
    }


    private void createMatchLayout(ArrayList<Match> liveMatches) {
        //Create dynamic matches and add event Listener to button of each match

        if (this.getActivity() != null && this.isAdded() && liveMatches.size() == 0) {

            this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textView = new TextView(getContext());
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setText("There are no live matches");
                    textView.setTextSize(20);

                    ImageView imageView = new ImageView(getContext());
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.basket));

                    binding.matchesLayoutContainer.addView(imageView);
                    binding.matchesLayoutContainer.addView(textView);

                }
            });

        }

        TeamService teamService = new TeamService();
        for (int i = 0; i < liveMatches.size(); i++) {

            if (LiveMatches.this.isAdded() && LiveMatches.this.getActivity() != null) {
                mapOfMatches.put(liveMatches.get(i).getId(), liveMatches.get(i));
                Pair<Team> pair = new Pair<Team>();
                View viewMatch = getLayoutInflater().inflate(R.layout.matches_live_layout, null);

                DAOLiveMatchService.getInstance().setListenerForPoints(LiveMatches.this,
                        viewMatch.findViewById(R.id.score_text),
                        liveMatches.get(i).getId(),
                        liveMatches.get(i).getTeamLandlord_id(),
                        liveMatches.get(i).getTeamguest_id());


                if (isAdmin) {
                    viewMatch.findViewById(R.id.imageButtonEditMatch).setVisibility(View.VISIBLE);
                } else {
                    viewMatch.findViewById(R.id.imageButtonEditMatch).setVisibility(View.INVISIBLE);
                }

                Thread team1Thread = teamService.findTeamById(liveMatches.get(i).getTeamLandlord_id(), new CallbackListener<Team>() {
                    @Override
                    public void callback(Team returnedObject) {
                        pair.teamLandlord = returnedObject;
                        View team1 = viewMatch.findViewById(R.id.team1);
                        UIHandler.updateTeamImageInMatch(LiveMatches.this, returnedObject, team1);
                        fillPlayers(returnedObject, viewMatch, true);
                    }
                });

                int finalI = i;
                Thread team2Thread = teamService.findTeamById(liveMatches.get(i).getTeamguest_id(), new CallbackListener<Team>() {
                    @Override
                    public void callback(Team returnedObject) {
                        pair.teamGuest = returnedObject;
                        View team2 = viewMatch.findViewById(R.id.team2);
                        UIHandler.updateTeamImageInMatch(LiveMatches.this, returnedObject, team2);
                        fillPlayers(returnedObject, viewMatch, false);
                        navigate(viewMatch, liveMatches.get(finalI).getId());

                    }
                });

                try {
                    team1Thread.join();
                    team2Thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                onclickView(viewMatch, liveMatches.get(i).getId());
                hashMap.put(liveMatches.get(i).getId(), pair);
                if (LiveMatches.this.isAdded() && LiveMatches.this.getActivity() != null) {

                    LiveMatches.this.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (binding != null) {
                                binding.matchesLayoutContainer.addView(viewMatch);
                            }
                        }
                    });
                }
            }
        }
    }

    public void navigate(View viewMatch, int matchid) {
        if (LiveMatches.this.getActivity() != null) {
            LiveMatches.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ImageButton imageButton = viewMatch.findViewById(R.id.imageButtonEditMatch);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("match", mapOfMatches.get(matchid));
                            bundle.putSerializable("teamLandlord", hashMap.get(matchid).teamLandlord);
                            bundle.putSerializable("teamGuest", hashMap.get(matchid).teamGuest);
                            NavHostFragment.findNavController(LiveMatches.this)
                                    .navigate(R.id.action_matchesTabContainer_to_adminsView, bundle);
                        }
                    });

                    viewMatch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("match", mapOfMatches.get(matchid));
                            bundle.putSerializable("teamLandlord", hashMap.get(matchid).teamLandlord);
                            bundle.putSerializable("teamGuest", hashMap.get(matchid).teamGuest);
                            NavHostFragment.findNavController(LiveMatches.this)
                                    .navigate(R.id.action_matchesTabContainer_to_tabContainer, bundle);
                        }
                    });

                }
            });
        }
    }

    public void fillPlayers(Team team, View viewMatch, boolean home) {
        PlayerService playerService = new PlayerService();

        Thread thread = playerService.findAllPlayersByTeamName(team.getTeamName(), new CallbackListener<ArrayList<Player>>() {
            @Override
            public void callback(ArrayList<Player> players) {
                team.setTeamPlayers(players);
                for (int i = 0; i < players.size(); i++) {
                    if (LiveMatches.this.isAdded() && LiveMatches.this.getActivity() != null) {
                        View playerView = getLayoutInflater().inflate(R.layout.player_layout, null);
                        View container = viewMatch.findViewById(R.id.matchPlayersInfo);
                        int layoutId = home ? R.id.homeTeamStartingPlayersVertical : R.id.awayTeamStartingPlayersVertical;
                        LinearLayout linearLayout = container.findViewById(layoutId);

                        int finalI = i;

                        if (LiveMatches.this.getActivity() != null) {
                            LiveMatches.this.requireActivity().runOnUiThread(new Runnable() {
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
                }
            }
        });
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
