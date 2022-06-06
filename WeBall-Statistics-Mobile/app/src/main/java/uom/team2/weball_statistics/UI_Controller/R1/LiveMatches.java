package uom.team2.weball_statistics.UI_Controller.R1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.Home;
import uom.team2.weball_statistics.databinding.FragmentHomeBinding;
import uom.team2.weball_statistics.databinding.FragmentLiveMatchesBinding;
import uom.team2.weball_statistics.databinding.FragmentSharedMatchesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveMatches#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveMatches extends Fragment {

    private FragmentLiveMatchesBinding binding;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LiveMatches() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveMatches.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveMatches newInstance(String param1, String param2) {
        LiveMatches fragment = new LiveMatches();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_live_matches, container, false);

        //Connection with button
        binding = FragmentLiveMatchesBinding.inflate(inflater, container, false);

        binding.include.dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LiveMatches.this).navigate(R.id.action_sharedMatches_to_liveMatchesDroppedDown);
            }
        });

        return binding.getRoot();




    }

}