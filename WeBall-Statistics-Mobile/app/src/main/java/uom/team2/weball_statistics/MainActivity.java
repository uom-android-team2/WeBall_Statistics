package uom.team2.weball_statistics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import org.json.JSONException;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.UI_Controller.best_starting5.BestStarting5;
import uom.team2.weball_statistics.UI_Controller.best_starting5.BestStarting5Factory;

import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;
import uom.team2.weball_statistics.Service.DAOLivePlayerStatistics;
import uom.team2.weball_statistics.Service.DAOLiveTeamService;



import uom.team2.weball_statistics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        new BestStarting5();

//        try {
//            new MatchesOnMainPage();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}