package uom.team2.weball_statistics.UI_Controller.LiveController.Comments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Statistics.LiveGameStatistics;
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

        //Create comments for Testing
        for (int i = 0; i <= 12; i++) {
            View comment = (View)getLayoutInflater().inflate(R.layout.match_comment_layout, null);
            if (i % 2 == 0) {
                comment.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_gray));
                comment.findViewById(R.id.card_view_comment).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_gray));
            }
            binding.commentsLayoutContainer.addView(comment);
        }

        //Testing parameters to Strings in strings.xml
        String text1 = String.format(getResources().getString(R.string.current_period), "1st");
        String text2 = String.format(getResources().getString(R.string.current_period), "2nd");
        String text3 = String.format(getResources().getString(R.string.shot_2_in), "Minas", "(Minas 2)");
        String text4 = String.format(getResources().getString(R.string.current_period), "4th");

        Toast.makeText(getContext(), text1, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), text2, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), text3, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), text4, Toast.LENGTH_SHORT).show();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
