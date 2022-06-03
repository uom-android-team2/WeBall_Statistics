package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.*;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentBestStarting5Binding;

public class BestStarting5 extends Fragment {
    private FragmentBestStarting5Binding binding;

    public BestStarting5() { }

    public static BestStarting5 getInstance(){
        return new BestStarting5();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBestStarting5Binding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public View createBestPlayerPerPositionLayout(int logo,
                                                  int playerPhoto,
                                                  String cityNameNumberPosition,
                                                  String firstnameLastname, int plusMinusValue){
        //Deriving View from layout fragment
        View bestPlayerPerPositionLayout = this.getLayoutInflater().inflate(R.layout.best_player_per_position_layout,null);
        //Changing View data based on input
            //City-Team name-Number-Position field
            TextView newCityNameNumberPosition = bestPlayerPerPositionLayout.findViewById(R.id.cityTeamNumberPosition);
            newCityNameNumberPosition.setText(cityNameNumberPosition);
            //Firstname Lastname field
            TextView newFirstnameLastname = bestPlayerPerPositionLayout.findViewById(R.id.firstnameLastname);
            newFirstnameLastname.setText(firstnameLastname);
            //Plus minus value field
            TextView newPlusMinusValue = bestPlayerPerPositionLayout.findViewById(R.id.efficiencyValue);
            String plusMinusValueString = Integer.toString(plusMinusValue);
            newPlusMinusValue.setText(plusMinusValueString);
            //Logo field
            ImageButton newLogo = bestPlayerPerPositionLayout.findViewById(R.id.logoImage);
            newLogo.setBackgroundResource(logo); //logo is of type int
            //Player photo field
            ImageButton newPlayerPhoto = bestPlayerPerPositionLayout.findViewById(R.id.playerImage);
            newPlayerPhoto.setBackgroundResource(playerPhoto); //playerPhoto is of type int

        return bestPlayerPerPositionLayout;
    }
}
