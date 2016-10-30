package dontbefa.hcmut.dontbefa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;

public class SignUpActivity extends AppCompatActivity {
    private AutoCompleteTextView mEmailSignUpView;
    private EditText mPasswordSignUpView;
    private EditText mPasswordSignUpCheckView;
    private EditText mUsernameSignUpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailSignUpView = (AutoCompleteTextView) findViewById(R.id.email_signup);
        mPasswordSignUpView = (EditText) findViewById(R.id.password_signup);
        mPasswordSignUpCheckView = (EditText) findViewById(R.id.repassword_signup);
        mUsernameSignUpView = (EditText) findViewById(R.id.account_name_signup);

        ActionProcessButton SignUpButton = (ActionProcessButton) findViewById(R.id.signup_button_signup);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpProcess();
            }
        });
    }

    private void SignUpProcess() {
        String email = mEmailSignUpView.getText().toString();
        String password = mPasswordSignUpView.getText().toString();
        String repassword = mPasswordSignUpCheckView.getText().toString();
        String username = mUsernameSignUpView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordSignUpView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordSignUpView;
            cancel = true;
        }

        if (!password.equals(repassword)) {
            mPasswordSignUpView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordSignUpView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailSignUpView.setError(getString(R.string.error_field_required));
            focusView = mEmailSignUpView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailSignUpView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailSignUpView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error
            focusView.requestFocus();
        } else {
            //TODO : kiểm tra đăng thông tin id, password Firebase
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            // TODO: Pass user info to main activity
            finish();
        }
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
