package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.BestPlayerPerPositionLayoutBinding;
import uom.team2.weball_statistics.utils.JSONHandler;
import uom.team2.weball_statistics.utils.Utils;


/*
 * @author Dionisis Lougaris ics20058
 */
public class BestStarting5UIController {

    public static BestStarting5UIController instance = new BestStarting5UIController();

    //Implement singleton pattern
    public static BestStarting5UIController getInstance(){
        if(instance == null){
            instance = new BestStarting5UIController();
        }
        return instance;
    }

    public void fillBestPointGuardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includePG;

        if(player.getTeamString().equals("Atlanta Hawks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hawks));
        }else if(player.getTeamString().equals("Brooklyn Nets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nets));
        }else if(player.getTeamString().equals("Boston Celtics")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Celtics));
        }else if(player.getTeamString().equals("Cleveland Cavaliers")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Cavaliers));
        }else if(player.getTeamString().equals("Dallas Mavericks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Mavericks));
        }else if(player.getTeamString().equals("Charlotte Hornets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hornets));
        }else if(player.getTeamString().equals("Chicago Bulls")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Bulls));
        }else if(player.getTeamString().equals("Denver Nuggets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nuggets));
        }else{
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.other));
        }
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;


        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | PG");
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        if(!"NOT FOUND".equals(player.getSurname())) {
        Picasso.get()
                .load(Config.PLAYER_IMAGES_RESOURCES+player.getImagePath())
                .resize(250, 400)
                .centerCrop()
                .into(imageViewPlayerPhoto);
        //Getting team logo if player exists
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url(Config.API_URL + "team.php?name=" + player.getTeamString())
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Team myTeam = JSONHandler.deserializeTeam(response.body().string());

            Picasso.get()
                    .load(Config.TEAM_IMAGES_RESOURCES + myTeam.getBadgePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewTeamLogo);
        }
    }
    public void fillBestShootingGuardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includeSG;

        if(player.getTeamString().equals("Atlanta Hawks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hawks));
        }else if(player.getTeamString().equals("Brooklyn Nets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nets));
        }else if(player.getTeamString().equals("Boston Celtics")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Celtics));
        }else if(player.getTeamString().equals("Cleveland Cavaliers")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Cavaliers));
        }else if(player.getTeamString().equals("Dallas Mavericks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Mavericks));
        }else if(player.getTeamString().equals("Charlotte Hornets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hornets));
        }else if(player.getTeamString().equals("Chicago Bulls")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Bulls));
        }else if(player.getTeamString().equals("Denver Nuggets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nuggets));
        }else{
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.other));
        }
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | SG");
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        //Getting team logo if player exists
        if(!"NOT FOUND".equals(player.getSurname())) {
            Picasso.get()
                    .load(Config.PLAYER_IMAGES_RESOURCES + player.getImagePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewPlayerPhoto);
            //Getting team logo
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url(Config.API_URL + "team.php?name=" + player.getTeamString())
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Team myTeam = JSONHandler.deserializeTeam(response.body().string());
            Picasso.get()
                    .load(Config.TEAM_IMAGES_RESOURCES + myTeam.getBadgePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewTeamLogo);
        }

    }
    public void fillBestSmallForwardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includeSF;

        if(player.getTeamString().equals("Atlanta Hawks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hawks));
        }else if(player.getTeamString().equals("Brooklyn Nets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nets));
        }else if(player.getTeamString().equals("Boston Celtics")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Celtics));
        }else if(player.getTeamString().equals("Cleveland Cavaliers")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Cavaliers));
        }else if(player.getTeamString().equals("Dallas Mavericks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Mavericks));
        }else if(player.getTeamString().equals("Charlotte Hornets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hornets));
        }else if(player.getTeamString().equals("Chicago Bulls")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Bulls));
        }else if(player.getTeamString().equals("Denver Nuggets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nuggets));
        }else{
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.other));
        }
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | SF");
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        //Getting team logo if player exists
        if(!"NOT FOUND".equals(player.getSurname())) {
            Picasso.get()
                    .load(Config.PLAYER_IMAGES_RESOURCES + player.getImagePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewPlayerPhoto);
            //Getting team logo
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url(Config.API_URL + "team.php?name=" + player.getTeamString())
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Team myTeam = JSONHandler.deserializeTeam(response.body().string());
            Picasso.get()
                    .load(Config.TEAM_IMAGES_RESOURCES + myTeam.getBadgePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewTeamLogo);
        }
    }
    public void fillBestPowerForwardInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includePF;

        if(player.getTeamString().equals("Atlanta Hawks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hawks));
        }else if(player.getTeamString().equals("Brooklyn Nets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nets));
        }else if(player.getTeamString().equals("Boston Celtics")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Celtics));
        }else if(player.getTeamString().equals("Cleveland Cavaliers")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Cavaliers));
        }else if(player.getTeamString().equals("Dallas Mavericks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Mavericks));
        }else if(player.getTeamString().equals("Charlotte Hornets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hornets));
        }else if(player.getTeamString().equals("Chicago Bulls")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Bulls));
        }else if(player.getTeamString().equals("Denver Nuggets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nuggets));
        }else{
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.other));
        }
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | PF");
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        //Getting team logo if player exists
        if(!"NOT FOUND".equals(player.getSurname())) {
            Picasso.get()
                    .load(Config.PLAYER_IMAGES_RESOURCES + player.getImagePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewPlayerPhoto);
            //Getting team logo
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url(Config.API_URL + "team.php?name=" + player.getTeamString())
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Team myTeam = JSONHandler.deserializeTeam(response.body().string());
            Picasso.get()
                    .load(Config.TEAM_IMAGES_RESOURCES + myTeam.getBadgePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewTeamLogo);
        }
    }
    public void fillBestCenterInfo(Player player, BestStarting5 bestStarting5Fragment) throws IOException, JSONException {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includeC;

        if(player.getTeamString().equals("Atlanta Hawks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hawks));
        }else if(player.getTeamString().equals("Brooklyn Nets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nets));
        }else if(player.getTeamString().equals("Boston Celtics")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Celtics));
        }else if(player.getTeamString().equals("Cleveland Cavaliers")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Cavaliers));
        }else if(player.getTeamString().equals("Dallas Mavericks")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Mavericks));
        }else if(player.getTeamString().equals("Charlotte Hornets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Hornets));
        }else if(player.getTeamString().equals("Chicago Bulls")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Bulls));
        }else if(player.getTeamString().equals("Denver Nuggets")){
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.Nuggets));
        }else{
            bestPlayerPerPositionLayoutBinding.background.setBackgroundColor(Utils.getColor(bestStarting5Fragment.getContext(), R.color.other));
        }
        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.logoImage;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.playerImage;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText(player.getTeamString()+" | #"+player.getNumber()+" | C");
        textViewPlayerName.setText(player.getName()+" "+player.getSurname());
        textViewEfValue.setText("+ "+player.getEfficiency());
        //Getting team logo if player exists
        if(!"NOT FOUND".equals(player.getSurname())) {
            Picasso.get()
                    .load(Config.PLAYER_IMAGES_RESOURCES + player.getImagePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewPlayerPhoto);
            //Getting team logo
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Request request = new Request.Builder()
                    .url(Config.API_URL + "team.php?name=" + player.getTeamString())
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            Team myTeam = JSONHandler.deserializeTeam(response.body().string());
            Picasso.get()
                    .load(Config.TEAM_IMAGES_RESOURCES + myTeam.getBadgePath())
                    .resize(250, 400)
                    .centerCrop()
                    .into(imageViewTeamLogo);
        }
    }
}
