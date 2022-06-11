package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveProgressUIController;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.BestPlayerPerPositionLayoutBinding;
import uom.team2.weball_statistics.utils.JSONHandler;
import uom.team2.weball_statistics.utils.Utils;

public class BestStarting5Controller {

    public static BestStarting5Controller instance = new BestStarting5Controller();

    //Implement singleton pattern
    public static BestStarting5Controller getInstance(){
        if(instance == null){
            instance = new BestStarting5Controller();
        }
        return instance;
    }

    public void fillBestPointGuardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includePG;

        bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.PG));
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;


        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | "+player.getPosition());
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+player.getImagePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewPlayerPhoto);
        //Getting team logo
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"team.php?name="+player.getTeamString())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        Team myTeam = JSONHandler.deserializeTeam(response.body().string());

        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+myTeam.getBadgePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewTeamLogo);
    }
    public void fillBestShootingGuardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includeSG;

        bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.SG));
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | "+player.getPosition());
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+player.getImagePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewPlayerPhoto);
        //Getting team logo
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"team.php?name="+player.getTeamString())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        Team myTeam = JSONHandler.deserializeTeam(response.body().string());
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+myTeam.getBadgePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewTeamLogo);

    }
    public void fillBestSmallForwardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includeSF;

        bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.SF));
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | "+player.getPosition());
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+player.getImagePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewPlayerPhoto);
        //Getting team logo
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"team.php?name="+player.getTeamString())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        Team myTeam = JSONHandler.deserializeTeam(response.body().string());
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+myTeam.getBadgePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewTeamLogo);
    }
    public void fillBestPowerForwardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includePF;

        bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.PF));
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | "+player.getPosition());
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+player.getImagePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewPlayerPhoto);
        //Getting team logo
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"team.php?name="+player.getTeamString())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        Team myTeam = JSONHandler.deserializeTeam(response.body().string());
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+myTeam.getBadgePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewTeamLogo);
    }
    public void fillBestCenterInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includeC;

        bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.C));
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | "+player.getPosition());
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+player.getImagePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewPlayerPhoto);
        //Getting team logo
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(Config.API_URL+"team.php?name="+player.getTeamString())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        Team myTeam = JSONHandler.deserializeTeam(response.body().string());
        Picasso.get()
                .load(Config.TEAM_IMAGES_RESOURCES+myTeam.getBadgePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewTeamLogo);
    }
}
