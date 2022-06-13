package uom.team2.weball_statistics.UI_Controller.LiveController.Progress;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.databinding.MatchInformationLayoutBinding;

public class LiveProgressUIController {

    public static LiveProgressUIController instance = new LiveProgressUIController();

    //Implement singleton pattern
    public static LiveProgressUIController getInstance(){
        if(instance == null){
            instance = new LiveProgressUIController();
        }
        return instance;
    }

    public void addActionForHomeTeam(LiveGameProgress liveGameProgress, Action action) {

        LayoutInflater layoutInflater = (LayoutInflater) liveGameProgress.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = liveGameProgress.getBinding().actionsLayoutContainer;
        View actionAsView = layoutInflater.inflate(R.layout.card_progress_layout_landlord, null);

        TextView time = (TextView) actionAsView.findViewById(R.id.time_happened);
        time.setText(action.getTimeHappened());

        ImageView picture = (ImageView) actionAsView.findViewById(R.id.action_happened_photo);

        TextView smallDescription = (TextView) actionAsView.findViewById(R.id.action_small_desc);
        smallDescription.setText(action.getActionDesc());

        linearLayout.addView(actionAsView, 0);
    }

    public void addActionForGuestTeam(LiveGameProgress liveGameProgress, Action action) {

        LayoutInflater layoutInflater = (LayoutInflater) liveGameProgress.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = liveGameProgress.getBinding().actionsLayoutContainer;
        View actionAsView = layoutInflater.inflate(R.layout.card_progress_layout_guest, null);

        TextView time = (TextView) actionAsView.findViewById(R.id.time_happened);
        time.setText(action.getTimeHappened());

        ImageView picture = (ImageView) actionAsView.findViewById(R.id.action_happened_photo);

        TextView smallDescription = (TextView) actionAsView.findViewById(R.id.action_small_desc);
        smallDescription.setText(action.getActionDesc());

        linearLayout.addView(actionAsView, 0);
    }

    public void addActionForGeneral(LiveGameProgress liveGameProgress, Action action) {

        LayoutInflater layoutInflater = (LayoutInflater) liveGameProgress.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = liveGameProgress.getBinding().actionsLayoutContainer;
        View actionAsView = layoutInflater.inflate(R.layout.card_progress_layout_general, null);

        TextView time = (TextView) actionAsView.findViewById(R.id.time_happened);
        time.setText(action.getTimeHappened());

        TextView smallDescription = (TextView) actionAsView.findViewById(R.id.action_small_desc);
        smallDescription.setText(action.getActionDesc());

        linearLayout.addView(actionAsView, 0);
    }

    public void noActionsMessage(LiveGameProgress liveGameProgress) {
        LayoutInflater layoutInflater = (LayoutInflater) liveGameProgress.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = liveGameProgress.getBinding().actionsLayoutContainer;
        View messageAsView = layoutInflater.inflate(R.layout.no_actions_message, null);

        linearLayout.addView(messageAsView);
    }

    public void displayQuarter(LiveGameProgress liveGameProgress, int quarter) {
        LayoutInflater layoutInflater = (LayoutInflater) liveGameProgress.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = liveGameProgress.getBinding().actionsLayoutContainer;
        View quarterView = layoutInflater.inflate(R.layout.quarter_layout, null);

        TextView quarterTextView = (TextView) quarterView.findViewById(R.id.quarter);
        quarterTextView.setText("Quarter " + quarter);

        linearLayout.addView(quarterView, 0);
    }

    public void fillMatchInformation(LiveGameProgress liveGameProgress, Match match) {
        MatchInformationLayoutBinding matchInformationLayoutBinding = liveGameProgress.getBinding().matchInfoInclude;

        TextView refereeNameTextView = (TextView) matchInformationLayoutBinding.headRefereeName;
        refereeNameTextView.setText(match.getReferee().getFirstname().charAt(0) + "." + match.getReferee().getSurname());

        TextView stadiumNameTextView = (TextView) matchInformationLayoutBinding.stadiumName;
        stadiumNameTextView.setText(match.getTeamLandlord().getTeamCity());;
    }
}
