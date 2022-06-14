package uom.team2.weball_statistics.UI_Controller.CompletedStatisticsController.TeamStats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.TeamChampioshipStatsService;
import uom.team2.weball_statistics.Service.TeamService;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.CallbackListener;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentTeamStatsBinding;
import uom.team2.weball_statistics.utils.Utils;

/*
 * @author Aravella Lousta ics20032
 */
public class TeamStatsFragment extends Fragment {

    private FragmentTeamStatsBinding binding;

    public TeamStatsFragment() {
        // Required empty public constructor
    }

    public static TeamStatsFragment newInstance() {
        TeamStatsFragment fragment = new TeamStatsFragment();
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
        binding = FragmentTeamStatsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeTitles();
        initializeColor();
    }

    @Override
    public void onStart() {
        super.onStart();
        TeamService teamService = new TeamService();
        TeamChampioshipStatsService teamChampioshipStatsService = new TeamChampioshipStatsService();

        teamChampioshipStatsService.getAllTeamStatistics(new CallbackListener<ArrayList<TeamStats>>() {
            @Override
            public void callback(ArrayList<TeamStats> returnedObject) {

                ArrayList<TeamStats> bestByPoints = new ArrayList<>();
                ArrayList<TeamStats> bestByAssists = new ArrayList<>();
                ArrayList<TeamStats> bestByBlocks = new ArrayList<>();
                ArrayList<TeamStats> bestByRebounds = new ArrayList<>();

                bestByPoints = sortByPoints(returnedObject);
                bestByAssists = sortByAssists(returnedObject);
                bestByBlocks = sortByBlocks(returnedObject);
                bestByRebounds = sortByRebounds(returnedObject);

                updateRows(teamService, bestByPoints, Type.POINTS);
                updateRows(teamService, bestByAssists, Type.ASSISTS);
                updateRows(teamService, bestByBlocks, Type.BLOCKS);
                updateRows(teamService, bestByRebounds, Type.REBOUNDS);

            }
        });

    }

    public void updateRows(TeamService teamService, ArrayList<TeamStats> teamStats, Type target) {
        int n = 5;

        if (teamStats.size() < 5) {
            n = teamStats.size();
        }

        for (int i = 0; i < n; i++) {
            int finalI = i;
            double value = -1;
            LinearLayout layout = binding.pointsPerGame.tableRowContainer;
            if (target == Type.POINTS) {
                value = teamStats.get(i).calculatePointsPercentage();
            } else if (target == Type.ASSISTS) {
                value = teamStats.get(i).calculateAssistPercentage();
                layout = binding.assistsPerGame.tableRowContainer;
            } else if (target == Type.BLOCKS) {
                value = teamStats.get(i).calculateBlockPercentage();
                layout = binding.blocksPerGame.tableRowContainer;
            } else if (target == Type.REBOUNDS) {
                value = teamStats.get(i).calculateReboundPercentage();
                layout = binding.reboundsPerGame.tableRowContainer;
            }

            double finalValue = value;
            LinearLayout finalLayout = layout;
            teamService.findTeamById(teamStats.get(i).getTeamId(), new CallbackListener<Team>() {
                @Override
                public void callback(Team returnedObject) {
                    try {
                        createRow(finalLayout,
                                Config.TEAM_IMAGES_RESOURCES + returnedObject.getBadgePath(),
                                returnedObject.getTeamName(), finalI + 1, finalValue + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

    public void createRow(LinearLayout linearLayout, String url, String name, int number, String score) throws InterruptedException {
        View view = linearLayout.getChildAt(number);
        if (TeamStatsFragment.this.getActivity() != null && TeamStatsFragment.this.isAdded()) {
            TeamStatsFragment.this.requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView scoreView = view.findViewById(R.id.score);
                    scoreView.setText(score);

                    TextView numberView = view.findViewById(R.id.number);
                    numberView.setText(String.valueOf(number));

                    TextView nameView = view.findViewById(R.id.teamName);
                    nameView.setText(name);

                    ImageView imageView = view.findViewById(R.id.badge);
                    Picasso.get()
                            .load(url)
                            .resize(200, 200)
                            .centerCrop()
                            .into(imageView);
                }
            });
        }
    }

    public void initializeColor() {
        binding.reboundsPerGame.tableRowContainer.getChildAt(1).setBackgroundColor(Utils.getColor(getContext(), R.color.alt_blue));
        binding.assistsPerGame.tableRowContainer.getChildAt(1).setBackgroundColor(Utils.getColor(getContext(), R.color.alt_blue));
        binding.blocksPerGame.tableRowContainer.getChildAt(1).setBackgroundColor(Utils.getColor(getContext(), R.color.alt_blue));
        binding.pointsPerGame.tableRowContainer.getChildAt(1).setBackgroundColor(Utils.getColor(getContext(), R.color.alt_blue));
    }

    public void initializeTitles() {
        binding.assistsPerGame.tableTitle.setText("Assists Per Game");
        binding.blocksPerGame.tableTitle.setText("Blocks Per Game");
        binding.reboundsPerGame.tableTitle.setText("Rebounds Per Game");

        changeLabel(binding.assistsPerGame.tableRowContainer, "APG");
        changeLabel(binding.reboundsPerGame.tableRowContainer, "RPG");
        changeLabel(binding.blocksPerGame.tableRowContainer, "BPG");

    }

    public void changeLabel(LinearLayout linearLayout, String value) {
        for (int i = 1; i < linearLayout.getChildCount(); i++) {
            TextView textView = linearLayout.getChildAt(i).findViewById(R.id.label);
            textView.setText(value);
        }
    }

    public ArrayList<TeamStats> sortByPoints(ArrayList<TeamStats> list) {

        Collections.sort(list, new Comparator<TeamStats>() {
            @Override
            public int compare(TeamStats teamStats, TeamStats t1) {
                if (t1.calculatePointsPercentage() - teamStats.calculatePointsPercentage() > 0) {
                    return 1;
                } else if (t1.calculatePointsPercentage() - teamStats.calculatePointsPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return new ArrayList<>(list);
    }

    public ArrayList<TeamStats> sortByAssists(ArrayList<TeamStats> list) {
        Collections.sort(list, new Comparator<TeamStats>() {
            @Override
            public int compare(TeamStats teamStats, TeamStats t1) {
                if (t1.calculateAssistPercentage() - teamStats.calculateAssistPercentage() > 0) {
                    return 1;
                } else if (t1.calculateAssistPercentage() - teamStats.calculateAssistPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return new ArrayList<>(list);
    }

    public ArrayList<TeamStats> sortByBlocks(ArrayList<TeamStats> list) {
        Collections.sort(list, new Comparator<TeamStats>() {
            @Override
            public int compare(TeamStats teamStats, TeamStats t1) {
                if (t1.calculateBlockPercentage() - teamStats.calculateBlockPercentage() > 0) {
                    return 1;
                } else if (t1.calculateBlockPercentage() - teamStats.calculateBlockPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return new ArrayList<>(list);
    }

    public ArrayList<TeamStats> sortByRebounds(ArrayList<TeamStats> list) {
        Collections.sort(list, new Comparator<TeamStats>() {
            @Override
            public int compare(TeamStats teamStats, TeamStats t1) {
                if (t1.calculateReboundPercentage() - teamStats.calculateReboundPercentage() > 0) {
                    return 1;
                } else if (t1.calculateReboundPercentage() - teamStats.calculateReboundPercentage() == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        return new ArrayList<>(list);
    }

    enum Type {POINTS, ASSISTS, BLOCKS, REBOUNDS}

}