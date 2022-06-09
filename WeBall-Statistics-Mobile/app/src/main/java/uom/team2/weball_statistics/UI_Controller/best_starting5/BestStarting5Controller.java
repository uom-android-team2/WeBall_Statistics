package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveProgressUIController;
import uom.team2.weball_statistics.databinding.BestPlayerPerPositionLayoutBinding;

public class BestStarting5Controller {

    public static BestStarting5Controller instance = new BestStarting5Controller();

    //Implement singleton pattern
    public static BestStarting5Controller getInstance(){
        if(instance == null){
            instance = new BestStarting5Controller();
        }
        return instance;
    }

    public void fillBestPointGuardInfo(Player player, BestStarting5 bestStarting5Fragment) {
        BestPlayerPerPositionLayoutBinding bestPlayerPerPositionLayoutBinding = bestStarting5Fragment.getBinding().includePG;

        ImageView imageViewTeamLogo = (ImageView) bestPlayerPerPositionLayoutBinding.imageView11;
        ImageView imageViewPlayerPhoto = (ImageView) bestPlayerPerPositionLayoutBinding.imageView9;
        TextView textViewPlayerInfo = (TextView) bestPlayerPerPositionLayoutBinding.cityTeamNumberPosition;
        TextView textViewPlayerName = (TextView) bestPlayerPerPositionLayoutBinding.firstnameLastname;
        TextView textViewEfValue = (TextView) bestPlayerPerPositionLayoutBinding.efficiencyValue;

        textViewPlayerInfo.setText("New info");
        textViewPlayerName.setText("Minas Charakopoulos");
        textViewEfValue.setText("10");
    }
}
