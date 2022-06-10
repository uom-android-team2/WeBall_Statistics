package uom.team2.weball_statistics.UI_Controller.Completed_Match_Stats;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.CompletedMatchHeaderLayoutBinding;
import uom.team2.weball_statistics.utils.JSONHandler;

public class CompletedMatchStatsUIController {

    public static CompletedMatchStatsUIController instance = new CompletedMatchStatsUIController();

    //Implement singleton pattern
    public static CompletedMatchStatsUIController getInstance(){
        if(instance == null){
            instance = new CompletedMatchStatsUIController();
        }
        return instance;
    }

    public void fillMatchHeaderInformation(CompletedMatchStats completedMatchStats,Match myMatch) {
        CompletedMatchHeaderLayoutBinding completedMatchHeaderLayoutBinding  =  completedMatchStats.getBinding().includeMatchHeader;

        ImageView imageViewHome = completedMatchHeaderLayoutBinding.team1Logo;
        ImageView imageViewAway = completedMatchHeaderLayoutBinding.team2Logo;
        TextView textViewScore = completedMatchHeaderLayoutBinding.scoreText;
        TextView textViewHomeTeamName = completedMatchHeaderLayoutBinding.team1Name;
        TextView textViewHomeTeamAway = completedMatchHeaderLayoutBinding.team2Name;
        TextView textViewMatchDate = completedMatchHeaderLayoutBinding.matchStartDate;

//        textViewScore.setText("43-54");
//        textViewHomeTeamName.setText("Paok");
//        textViewHomeTeamAway.setText("Aris");
//        textViewMatchDate.setText("14/05/2022 17:00");
//        int homeTeamId = myMatch.getTeamLandlord_id();
//        int awayTeamId = myMatch.getTeamguest_id();
        textViewScore.setText("43-54");
        textViewHomeTeamName.setText("Paok");
        textViewHomeTeamAway.setText("Aris");
        textViewMatchDate.setText("14/05/2022 17:00");

        //Getting team logo
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        Request request = new Request.Builder()
//                .url(Config.API_URL+"team.php?name="+player.getTeamString())
//                .method("GET", null)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//        Team myTeam = JSONHandler.deserializeTeam(response.body().string());
//        Picasso.get()
//                .load(Config.TEAM_IMAGES_RESOURCES+myTeam.getBadgePath())
//                .resize(250, 400)
//                .centerCrop()
//                .into(imageViewTeamLogo);
    }
}
