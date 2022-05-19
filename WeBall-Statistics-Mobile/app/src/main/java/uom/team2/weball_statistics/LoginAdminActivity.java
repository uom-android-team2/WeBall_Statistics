package uom.team2.weball_statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginAdminActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvUsername = (TextView) findViewById(R.id.textUsername);
        etPassword = (EditText) findViewById(R.id.textPassword);
        addHandlerLogin();

    }

    private void addHandlerLogin(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> usernamePassword = getUsernamePassword();

            }
        });

        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){

                    ArrayList<String> usernamePassword = getUsernamePassword();

                    return true;
                }
                return false;

            }
        });
    }

    private ArrayList<String> getUsernamePassword(){
        //Take username and password.
        String username = tvUsername.getText().toString();
        String password = etPassword.getText().toString();

        // Add username and password in the arraylist that will be returned.
        ArrayList<String> returnedArrayList = new ArrayList<String>();
        returnedArrayList.add(username);
        returnedArrayList.add(password);

        return returnedArrayList;

    }
}