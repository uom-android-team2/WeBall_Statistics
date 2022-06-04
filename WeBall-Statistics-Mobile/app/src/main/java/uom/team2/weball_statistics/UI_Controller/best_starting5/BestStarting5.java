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
        createBestPlayerPerPositionPG(1,2,"Chicago Bulls","Lonzo Ball"
        ,23);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public View createBestPlayerPerPositionPG(int logo,
                                                  int playerPhoto,
                                                  String cityNameNumberPosition,
                                                  String firstnameLastname, int efficiencyValue){
        //Deriving View from layout fragment
        View bestPlayerPerPositionLayout = this.getLayoutInflater().inflate(R.layout.fragment_best_starting5,null);
        View mylayout = bestPlayerPerPositionLayout.findViewById(R.id.linearLayoutId);
        View include = mylayout.findViewById(R.id.includePG);
        //Changing View data based on input
            //City-Team name-Number-Position field
            TextView newCityNameNumberPosition = include.findViewById(R.id.cityTeamNumberPosition);
            newCityNameNumberPosition.setText(cityNameNumberPosition);
            //Firstname Lastname field
            TextView newFirstnameLastname = include.findViewById(R.id.firstnameLastname);
            newFirstnameLastname.setText(firstnameLastname);
            //Plus minus value field
            TextView newPlusMinusValue = include.findViewById(R.id.efficiencyValue);
            String plusMinusValueString = Integer.toString(efficiencyValue);
            newPlusMinusValue.setText(plusMinusValueString);
            //Logo field
//            ImageButton newLogo = include.findViewById(R.id.logoImage);
//            newLogo.setBackgroundResource(logo); //logo is of type int
            //Player photo field
//            ImageButton newPlayerPhoto = include.findViewById(R.id.playerImage);
//            newPlayerPhoto.setBackgroundResource(playerPhoto); //playerPhoto is of type int

        return bestPlayerPerPositionLayout;
    }
}
