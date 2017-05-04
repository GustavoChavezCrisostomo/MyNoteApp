package practica.tecsup.edu.mynoteapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import practica.tecsup.edu.mynoteapp.R;
import practica.tecsup.edu.mynoteapp.models.User;
import practica.tecsup.edu.mynoteapp.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText emailInput;
    private EditText passwordInput;
    private ProgressBar progressBar;
    private View loginPanel;

    //register
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        loginPanel = findViewById(R.id.login_panel);

        sharedPreferences = this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE);

        // username remember
        String username = sharedPreferences.getString("username", null);
        if(username != null){
            emailInput.setText(username);
            passwordInput.requestFocus();
        }

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }
    }

    public void callLogin(View view){
        loginPanel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        String username = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Login logic
        User user = UserRepository.login(username, password);

        if(user == null){
            Toast.makeText(this, "Username or password invalid", Toast.LENGTH_SHORT).show();
            loginPanel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

        // Save to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor
                .putString("username", user.getUsername())
                .putBoolean("islogged", true)
                .commit();

        // Go to Dashboard
        goDashboard();
    }

    private void goDashboard(){
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    //register
    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }


}
