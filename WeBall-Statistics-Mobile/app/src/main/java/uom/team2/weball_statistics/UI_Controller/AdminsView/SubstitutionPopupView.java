package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import uom.team2.weball_statistics.Model.Actions.Action;
import uom.team2.weball_statistics.Model.Actions.BelongsTo;
import uom.team2.weball_statistics.Model.Actions.Substitution.Substitution;
import uom.team2.weball_statistics.Model.Actions.Substitution.SubstitutionComment;
import uom.team2.weball_statistics.Model.Match;
import uom.team2.weball_statistics.Model.Player;
import uom.team2.weball_statistics.Model.Team;
import uom.team2.weball_statistics.R;
import uom.team2.weball_statistics.Service.DAOAction;
import uom.team2.weball_statistics.configuration.Config;

public class SubstitutionPopupView extends Dialog {

    private final Player swapPlayer;
    private final Team team;
    private final int playerChecked;
    private final HashMap<View, Integer> map = new HashMap<>();
    public Activity c;
    public Dialog d;
    public TextView sub1, sub2, sub3, sub4, sub5, xbutton;
    private String str;
    private String n;
    private Player helperVariable;
    private ArrayList<ImageView> views = new ArrayList<>();
    private String timeHappened;
    private Match match;

    public SubstitutionPopupView(Activity a, Player p, Team t, int pChecked, ArrayList<ImageView> views, String timeHappened, Match match) {
        super(a);
        swapPlayer = p;
        team = t;
        this.playerChecked = pChecked - 1;
        this.c = a;
        this.views = views;
        this.timeHappened = timeHappened;
        this.match = match;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.substitution_popup_view);


        xbutton = findViewById(R.id.x_button);
        LinearLayout linearLayout = findViewById(R.id.container);
        int i = 0;
        for (Player player : team.getSubPlayers()) {
            View view = getLayoutInflater().inflate(R.layout.substitiution_layout, null);
            TextView textView = view.findViewById(R.id.sub1_text2);
            textView.setText(player.getName().toUpperCase(Locale.ROOT).charAt(0) + ". " + player.getSurname());
            ImageView imageView = view.findViewById(R.id.sub1_image2);
            Picasso.get().load(Config.PLAYER_IMAGES_RESOURCES + player.getImagePath())
                    .centerCrop()
                    .resize(200, 200).into(imageView);

            linearLayout.addView(view);
            map.put(view, i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helperVariable = team.getSubPlayers().get(map.get(v));
                    team.setSubPlayers(map.get(v), swapPlayer);
                    team.setKeyPlayers(playerChecked, helperVariable);

                    insertSubAction(); //Insert sub action to firebase

                    Picasso.get().load(Config.PLAYER_IMAGES_RESOURCES + helperVariable.getImagePath())
                            .centerCrop()
                            .resize(200, 200)
                            .into(views.get(playerChecked));
                    dismiss();
                }
            });
            i++;
        }


        xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void insertSubAction() {
        Action substitutionAction = null;
        Action substitutionComment = null;
        if (team.getId() == match.getTeamLandlord_id()) {
            substitutionAction = new Substitution(timeHappened, BelongsTo.HOME, helperVariable, swapPlayer,  team);
            substitutionComment = new SubstitutionComment(timeHappened, BelongsTo.HOME, helperVariable, swapPlayer,  team, getContext());
        } else {
            substitutionAction = new Substitution(timeHappened, BelongsTo.GUEST, helperVariable, swapPlayer, team);
            substitutionComment = new SubstitutionComment(timeHappened, BelongsTo.GUEST, helperVariable, swapPlayer,  team, getContext());
        }

        if (substitutionAction != null) {
            DAOAction.getInstance().insertAction(substitutionAction, match);
        }

        if (substitutionComment != null) {
            DAOAction.getInstance().insertCommentDesc(substitutionComment, match);
        }
    }

}