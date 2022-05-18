package uom.team2.weball_statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginAdminActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        username = (TextView) findViewById(R.id.textUsername);
        password = (TextView) findViewById(R.id.textPassword);
        addHandlerLogin();

    }



    private void addHandlerLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){

                    return true;
                }
                return false;

            }
        });
    }
}