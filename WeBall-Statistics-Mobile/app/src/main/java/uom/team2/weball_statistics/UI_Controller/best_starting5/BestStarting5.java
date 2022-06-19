package uom.team2.weball_statistics.UI_Controller.best_starting5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.*;

import org.json.JSONException;

import java.io.IOException;

import uom.team2.weball_statistics.databinding.FragmentBestStarting5Binding;


/*
 * @author Dionisis Lougaris ics20058
 */
public class BestStarting5 extends Fragment {

    private FragmentBestStarting5Binding binding;
    private BestStarting5UIController bestStarting5UIController = BestStarting5UIController.getInstance();

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
            BestStarting5Model bs5 = new BestStarting5Model();
            this.bestStarting5UIController.fillBestPointGuardInfo(bs5.getBestPG(), this);
            this.bestStarting5UIController.fillBestShootingGuardInfo(bs5.getBestSG(), this);
            this.bestStarting5UIController.fillBestSmallForwardInfo(bs5.getBestSF(), this);
            this.bestStarting5UIController.fillBestPowerForwardInfo(bs5.getBestPF(), this);
            this.bestStarting5UIController.fillBestCenterInfo(bs5.getBestC(), this);

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
