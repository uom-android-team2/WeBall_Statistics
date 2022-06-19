package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.FlowType;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlow;
import uom.team2.weball_statistics.Model.Actions.MatchFlow.MatchFlowComment;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Statistics.DBDataRecovery;
import uom.team2.weball_statistics.Model.Statistics.Stats;
import uom.team2.weball_statistics.Model.Statistics.TeamStats;
import uom.team2.weball_statistics.Model.Status;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.Service.MatchService;
import uom.team2.weball_statistics.configuration.Config;
import uom.team2.weball_statistics.databinding.FragmentAdminsViewBinding;

public class FinishGameDialog extends Dialog implements android.view.View.OnClickListener {

    private FragmentAdminsViewBinding fragmentAdminsViewBinding;
    private Match match;
    private Stats teamLandlordStats;
    private Stats teamGuestStats;
    private DBDataRecovery teamsPlayed;
    private Context ct;

    public FinishGameDialog(@NonNull Context context, FragmentAdminsViewBinding fragmentAdminsViewBinding, Match match,
                            Stats teamLandlordStats, Stats teamGuestStats, DBDataRecovery teamsPlayed) {
        super(context);
        this.ct = context;
        this.fragmentAdminsViewBinding = fragmentAdminsViewBinding;
        this.match = match;
        this.teamLandlordStats = teamLandlordStats;
        this.teamGuestStats = teamGuestStats;
        this.teamsPlayed = teamsPlayed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_popup_view);

        TextView textMessage = findViewById(R.id.dialog_info);
        textMessage.setText("Are you sure you want to end the match?");

        findViewById(R.id.dialog_Yes).setOnClickListener(this);
        findViewById(R.id.dialog_No).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_Yes:

                String[] scores = fragmentAdminsViewBinding.scoreText.getText().toString().trim().split("-");
                int scoreTeamLandlord = Integer.parseInt(scores[0].trim());
                int scoreTeamGuest = Integer.parseInt(scores[1].trim());

                this.fragmentAdminsViewBinding.clock.stop();
                this.fragmentAdminsViewBinding.startButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
                this.fragmentAdminsViewBinding.startButton.setText("Finished");
                this.fragmentAdminsViewBinding.undoButton.setText("-");
                this.fragmentAdminsViewBinding.pauseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
                this.fragmentAdminsViewBinding.pauseButton.setText("-");
                match.setStatus(Status.COMPLETED);
                this.disableButtonsColorOnAdminsPanel(120);
                this.disableButtonsOnAdminsPanel();

                //Add completed action description to firebase
                Action completedMatchAction = new MatchFlow(this.fragmentAdminsViewBinding.clock.getText().toString(), FlowType.COMPLETED);
                DAOAction.getInstance().insertAction(completedMatchAction, match);
                //Add completed comment description to firebase
                Action completedMatchComment = new MatchFlowComment(this.fragmentAdminsViewBinding.clock.getText().toString(), FlowType.COMPLETED, getContext());
                DAOAction.getInstance().insertCommentDesc(completedMatchComment, match);

                match.setCompleted(true);
                match.setProgress(0);
                MatchService ms = new MatchService();
                try {
                    ms.statusUpdate(match);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (teamLandlordStats != null && teamGuestStats != null) {

                    if (scoreTeamLandlord > scoreTeamGuest) {
                        ((TeamStats) teamLandlordStats).setWins();
                        ((TeamStats) teamGuestStats).setLoses();
                    }

                    if (scoreTeamLandlord < scoreTeamGuest) {
                        ((TeamStats) teamGuestStats).setWins();
                        ((TeamStats) teamLandlordStats).setLoses();
                    }
                }

                try {
                    teamsPlayed.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamLandlordStats);
                    teamsPlayed.updateDataDB(Config.API_ΤΕΑΜ_STATISTICS_COMPLETED, teamGuestStats);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                dismiss();
                break;
            case R.id.dialog_No:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void disableButtonsOnAdminsPanel() {
        this.fragmentAdminsViewBinding.freethrowButton.setEnabled(false);
        this.fragmentAdminsViewBinding.twoPointerButton.setEnabled(false);
        this.fragmentAdminsViewBinding.threePointerButton.setEnabled(false);
        this.fragmentAdminsViewBinding.reboundButton.setEnabled(false);
        this.fragmentAdminsViewBinding.assistButton.setEnabled(false);
        this.fragmentAdminsViewBinding.stealButton.setEnabled(false);
        this.fragmentAdminsViewBinding.blockButton.setEnabled(false);
        this.fragmentAdminsViewBinding.foulButton.setEnabled(false);
        this.fragmentAdminsViewBinding.turnoverButton.setEnabled(false);
        this.fragmentAdminsViewBinding.substitutionButton.setEnabled(false);
        this.fragmentAdminsViewBinding.startButton.setEnabled(false);
        this.fragmentAdminsViewBinding.pauseButton.setEnabled(false);
        this.fragmentAdminsViewBinding.undoButton.setEnabled(false);
    }

    public void disableButtonsColorOnAdminsPanel(int alpha) {
        this.fragmentAdminsViewBinding.freethrowButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.twoPointerButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.threePointerButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.reboundButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.assistButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.stealButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.blockButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.foulButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.turnoverButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.substitutionButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.startButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.pauseButton.getBackground().setAlpha(alpha);
        this.fragmentAdminsViewBinding.undoButton.getBackground().setAlpha(alpha);
    }
}
