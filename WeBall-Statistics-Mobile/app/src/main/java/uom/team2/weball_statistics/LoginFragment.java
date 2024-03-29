package uom.team2.weball_statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.IOException;

import uom.team2.weball_statistics.Model.LoginAdmin;
import uom.team2.weball_statistics.UIFactory.LayoutFactory;
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
        if (binding != null) {
            username = binding.textUsername;
            password = binding.textPassword;
            loginButton = binding.btnLogin;

            loginButton.setOnClickListener(e -> checkLogin());
        }
    }

    private void checkLogin() {
        if (password.getText().length() >= 6 && username.getText().length() >= 1) {
            LoginAdmin a = new LoginAdmin(username.getText().toString(), password.getText().toString());
            try {
                if (a.isAdminInDB()) {
                    username.setText("");
                    password.setText("");
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isAdmin", true);
                    bundle.putString("username", username.getText().toString());
                    NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_matchesTabContainer, bundle);
                } else {
                    username.setText("");
                    password.setText("");

                    LayoutFactory.createSnackbar(binding.getRoot(), "No correct Credits.Please try again", R.color.red).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            username.setText("");
            password.setText("");
            LayoutFactory.createSnackbar(binding.getRoot(), "Invalid username or password format.Please try again", R.color.red).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}