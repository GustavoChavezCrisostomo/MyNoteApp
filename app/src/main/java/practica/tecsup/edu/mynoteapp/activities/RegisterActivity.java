package practica.tecsup.edu.mynoteapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import practica.tecsup.edu.mynoteapp.R;
import practica.tecsup.edu.mynoteapp.repositories.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText repasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = (EditText)findViewById(R.id.fullname_input);
        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
        repasswordInput = (EditText)findViewById(R.id.repassword_input);
    }

    public void callRegister(View view){
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String repassword = repasswordInput.getText().toString();

        if (repassword == password) {
            if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        UserRepository.create(fullname, email, password);

        finish();

    }
}
