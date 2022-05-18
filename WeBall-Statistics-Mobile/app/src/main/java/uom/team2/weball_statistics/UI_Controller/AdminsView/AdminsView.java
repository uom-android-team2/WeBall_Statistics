package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminsView extends Fragment {
    private FragmentAdminsViewBinding binding;
    private Chronometer chronometer;
    private boolean running=false;
    private Button start_end_button;
    private long pauseOffset;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminsView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminsViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminsView newInstance(String param1, String param2) {
        AdminsView fragment = new AdminsView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAdminsViewBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }
//Start Button
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!running) {
                   binding.clock.setBase(SystemClock.elapsedRealtime());
                   binding.clock.start();
                    running = true;

                }
            }

        });
//Pause Button
        binding.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!running) {
                    binding.clock.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    binding.clock.start();
                    running = true;

                } else {
                    binding.clock.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - binding.clock.getBase();
                    running = false;
                }
            }
        });

//Freethrow Button
        



    }






}