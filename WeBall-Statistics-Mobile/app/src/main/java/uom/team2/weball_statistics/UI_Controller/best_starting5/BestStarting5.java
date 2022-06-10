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

        this.bestStarting5Controller.fillBestPointGuardInfo(null, this);

       // createBestPlayerPerPositionPG(1,2,"Chicago Bulls","Lonzo Ball"
        //,23);
        Thread as = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    BestStarting5Factory bs5 = new BestStarting5Factory();
                    Player bestPG = bs5.getBestPG();
                    Player bestSG = bs5.getBestSG();
                    Player bestSF = bs5.getBestSF();
                    Player bestPF = bs5.getBestPF();
                    Player bestC = bs5.getBestC();
                    System.out.println("----------Best per position----------");
                    System.out.println(bestPG.getPosition()+" "+bestPG.getName());
                    System.out.println(bestSG.getPosition()+" "+bestSG.getName());
                    System.out.println(bestSF.getPosition()+" "+bestSF.getName());
                    System.out.println(bestPF.getPosition()+" "+bestPF.getName());
                    System.out.println(bestC.getPosition()+" "+bestC.getName());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        as.start();
        try {
            as.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
