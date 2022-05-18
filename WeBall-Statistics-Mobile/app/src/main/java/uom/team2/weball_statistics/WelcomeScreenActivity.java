package uom.team2.weball_statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreenActivity extends AppCompatActivity {

    private Button btnLoginAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        btnLoginAdmin = (Button) findViewById(R.id.btnLoginAdmin);
        addHandlerBTNLoginAdmin(this);


    }

    private void addHandlerBTNLoginAdmin(AppCompatActivity a){
        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent i = new Intent(a, LoginAdminActivity.class);
                startActivity(i);


            }
        });
    }
}