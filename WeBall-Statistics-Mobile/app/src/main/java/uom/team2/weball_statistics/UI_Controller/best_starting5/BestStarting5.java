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

import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveGameProgress;
import uom.team2.weball_statistics.databinding.BestPlayerPerPositionLayoutBinding;
import uom.team2.weball_statistics.databinding.FragmentBestStarting5Binding;
import uom.team2.weball_statistics.databinding.FragmentLiveGameProgressBinding;
import uom.team2.weball_statistics.databinding.MatchInformationLayoutBinding;

public class BestStarting5 extends Fragment {
    private BestStarting5Factory factory;
    private FragmentBestStarting5Binding binding;



        public BestStarting5() {
    //    Thread as = new Thread(new Runnable() {
//        @Override
//        public void run() {
//
//            try {
//            factory = new BestStarting5Factory();
//            bestPG = factory.getBestPG();
//            bestSG = factory.getBestSG();
//            bestSF = factory.getBestSF();
//            bestPF = factory.getBestPF();
//            bestC = factory.getBestC();
//
//            } catch (JSONException e) {
//               e.printStackTrace();
//          } catch (IOException e) {
//               e.printStackTrace();
//         }
//        }
//    });
//        as.start();
//        try {
//            as.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
}

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

        navigate();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

     public void navigate(){
        binding.bestStarting5Title.setOnClickListener(e ->{
            NavHostFragment.findNavController(this).navigate(R.id.action_bestStarting5_to_sharedTabContainer);
        });
     }
    public FragmentBestStarting5Binding getBinding() {
        return binding;
    }



    public BestStarting5Factory getFactory() {
        return factory;
    }
}
