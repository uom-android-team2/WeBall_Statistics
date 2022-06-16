package uom.team2.weball_statistics.UI_Controller.LiveController.Comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
    }

    public void addCommentForGuestTeam(LiveGameComments liveGameCommentsFragment, Action action) {
    }

    public void addCommentForGeneral(LiveGameComments liveGameCommentsFragment, Action action) {
    }
}
