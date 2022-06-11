package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.*;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONException;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentBestStarting5Binding;

public class BestStarting5 extends Fragment {

    private FragmentBestStarting5Binding binding;
    private BestStarting5Controller bestStarting5Controller = BestStarting5Controller.getInstance();

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
        try {
            BestStarting5Factory bs5 = new BestStarting5Factory();
            this.bestStarting5Controller.fillBestPointGuardInfo(bs5.getBestPG(), this);
            this.bestStarting5Controller.fillBestShootingGuardInfo(bs5.getBestSG(), this);
            this.bestStarting5Controller.fillBestSmallForwardInfo(bs5.getBestSF(), this);
            this.bestStarting5Controller.fillBestPowerForwardInfo(bs5.getBestPF(), this);
            this.bestStarting5Controller.fillBestCenterInfo(bs5.getBestC(), this);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //calling the method that adds data on GUI

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public FragmentBestStarting5Binding getBinding() {
        return binding;
    }
}
