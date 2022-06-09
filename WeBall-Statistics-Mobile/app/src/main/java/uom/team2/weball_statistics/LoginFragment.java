package uom.team2.weball_statistics;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import uom.team2.weball_statistics.Model.LoginAdmin;
import uom.team2.weball_statistics.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private EditText username;
    private EditText password;
    private Button loginButton;


    public LoginFragment() {



    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        username = (EditText) binding.textUsername;
        password =  (EditText) binding.textPassword;
        loginButton = (Button) binding.btnLogin;
        ImageView imageView = (ImageView) binding.badge;

        imageView.setOnClickListener(e -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_matchesTabContainer);
        });


        loginButton.setOnClickListener(e -> checkLogin());



    }

    private void checkLogin() {
        if(password.getText().length() >= 6 && username.getText().length() >= 1){
          LoginAdmin a = new LoginAdmin(username.getText().toString(), password.getText().toString());
            try {
                if(a.isAdminInDB()){
                    username.setText("");
                    password.setText("");
                    NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_adminsView);
                }else{
                    username.setText("");
                    password.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "No correct Credits.Please try again", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentLoginBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }
}