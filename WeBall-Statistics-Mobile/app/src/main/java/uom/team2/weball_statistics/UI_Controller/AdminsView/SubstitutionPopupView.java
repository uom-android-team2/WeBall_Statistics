package uom.team2.weball_statistics.UI_Controller.AdminsView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import uom.team2.weball_statistics.R;

public class SubstitutionPopupView extends Dialog implements
        android.view.View.OnClickListener{

    public Activity c;
    public Dialog d;
    public TextView sub1, sub2,sub3,sub4,sub5,xbutton;
    private String str;
    private String n;



    public SubstitutionPopupView(Activity a,String name) {
        super(a);
        n=name;
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.substitution_popup_view);


        xbutton=(TextView) findViewById(R.id.x_button);
        sub1 = (TextView) findViewById(R.id.sub1_text);
        sub2 = (TextView) findViewById(R.id.sub2_text);
        sub3 = (TextView) findViewById(R.id.sub2_text);
        sub4 = (TextView) findViewById(R.id.sub2_text);
        sub5 = (TextView) findViewById(R.id.sub2_text);




        xbutton.setOnClickListener(this);
        sub1.setOnClickListener(this);
        sub2.setOnClickListener(this);
        sub3.setOnClickListener(this);
        sub4.setOnClickListener(this);
        sub5.setOnClickListener(this);

        //


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.x_button:
                dismiss();
                break;

            case R.id.sub1_text:


                dismiss();
                break;
            case R.id.sub2_text:
                dismiss();
                break;
            case R.id.sub3_text:


                //dismiss();
                break;
            case R.id.sub4_text:


                //dismiss();
                break;
            case R.id.sub5_text:


                //dismiss();
                break;

            default:
                break;
        }
        dismiss();
    }
}