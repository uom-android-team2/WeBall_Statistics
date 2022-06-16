package uom.team2.weball_statistics.UI_Controller.LiveController.Comments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.UI_Controller.LiveController.Progress.LiveGameProgress;

public class LiveCommentsUIController {

    public static LiveCommentsUIController instance = new LiveCommentsUIController();

    //Implement singleton pattern
    public static LiveCommentsUIController getInstance(){
        if(instance == null){
            instance = new LiveCommentsUIController();
        }
        return instance;
    }

    public void noActionsMessage(LiveGameComments liveGameComments) {

        if (liveGameComments.isAdded() && liveGameComments.getActivity() != null) {

            LayoutInflater layoutInflater = (LayoutInflater) liveGameComments.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = liveGameComments.getBinding().commentsLayoutContainer;
            View messageAsView = layoutInflater.inflate(R.layout.no_actions_message, null);

            linearLayout.addView(messageAsView);
        }
    }

    public void addCommentForHomeTeam(LiveGameComments liveGameCommentsFragment, Action action) {

        if (liveGameCommentsFragment.isAdded() && liveGameCommentsFragment.getActivity() != null) {

            LayoutInflater layoutInflater = (LayoutInflater) liveGameCommentsFragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = liveGameCommentsFragment.getBinding().commentsLayoutContainer;
            View commentAsView = layoutInflater.inflate(R.layout.comment_match_home_layout, null);

            TextView time = (TextView) commentAsView.findViewById(R.id.time_happened);
            time.setText(action.getTimeHappened());

            ImageView picture = (ImageView) commentAsView.findViewById(R.id.imageView);
            String uri = "@drawable/" + action.getImageAction();  // where myresource (without the extension) is the file
            int imageResource = liveGameCommentsFragment.getActivity().getResources().getIdentifier(uri, null, liveGameCommentsFragment.getActivity().getPackageName());
            Drawable res = liveGameCommentsFragment.getResources().getDrawable(imageResource);
            picture.setImageDrawable(res);

            TextView commentDescription = (TextView) commentAsView.findViewById(R.id.descTextView);
            commentDescription.setText(action.getActionDesc());

            linearLayout.addView(commentAsView, 0);
        }
    }

    public void addCommentForGuestTeam(LiveGameComments liveGameCommentsFragment, Action action) {

        if (liveGameCommentsFragment.isAdded() && liveGameCommentsFragment.getActivity() != null) {

            LayoutInflater layoutInflater = (LayoutInflater) liveGameCommentsFragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = liveGameCommentsFragment.getBinding().commentsLayoutContainer;
            View commentAsView = layoutInflater.inflate(R.layout.comment_match_away_layout, null);

            TextView time = (TextView) commentAsView.findViewById(R.id.time_happened);
            time.setText(action.getTimeHappened());

            ImageView picture = (ImageView) commentAsView.findViewById(R.id.imageView);
            String uri = "@drawable/" + action.getImageAction();  // where myresource (without the extension) is the file
            int imageResource = liveGameCommentsFragment.getActivity().getResources().getIdentifier(uri, null, liveGameCommentsFragment.getActivity().getPackageName());
            Drawable res = liveGameCommentsFragment.getResources().getDrawable(imageResource);
            picture.setImageDrawable(res);

            TextView commentDescription = (TextView) commentAsView.findViewById(R.id.descTextView);
            commentDescription.setText(action.getActionDesc());

            linearLayout.addView(commentAsView, 0);
        }
    }

    public void addCommentForGeneral(LiveGameComments liveGameCommentsFragment, Action action) {

        if (liveGameCommentsFragment.isAdded() && liveGameCommentsFragment.getActivity() != null) {

            LayoutInflater layoutInflater = (LayoutInflater) liveGameCommentsFragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = liveGameCommentsFragment.getBinding().commentsLayoutContainer;
            View commentAsView = layoutInflater.inflate(R.layout.comment_match_general_layout, null);

            TextView time = (TextView) commentAsView.findViewById(R.id.time_happened);
            time.setText(action.getTimeHappened());

            ImageView picture = (ImageView) commentAsView.findViewById(R.id.imageView);
            String uri = "@drawable/" + action.getImageAction();  // where myresource (without the extension) is the file
            int imageResource = liveGameCommentsFragment.getActivity().getResources().getIdentifier(uri, null, liveGameCommentsFragment.getActivity().getPackageName());
            Drawable res = liveGameCommentsFragment.getResources().getDrawable(imageResource);
            picture.setImageDrawable(res);

            TextView commentDescription = (TextView) commentAsView.findViewById(R.id.descTextView);
            commentDescription.setText(action.getActionDesc());

            linearLayout.addView(commentAsView, 0);
        }
    }
}
