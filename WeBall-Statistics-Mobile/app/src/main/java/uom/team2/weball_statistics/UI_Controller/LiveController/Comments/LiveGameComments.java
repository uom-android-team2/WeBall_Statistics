package uom.team2.weball_statistics.UI_Controller.LiveController.Comments;

import android.os.Bundle;
import android.view.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.FragmentLiveGameCommentsBinding;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class LiveGameComments extends Fragment {

    private FragmentLiveGameCommentsBinding binding;

    public LiveGameComments() { }

    public static LiveGameComments getInstance(){
        return new LiveGameComments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLiveGameCommentsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Create comments for Testing
        for (int i = 0; i <= 12; i++) {
            View comment = (View)getLayoutInflater().inflate(R.layout.match_comment_layout, null);
            binding.commentsLayoutContainer.addView(comment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
