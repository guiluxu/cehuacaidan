package com.example.mynewsclient.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynewsclient.R;
import com.example.mynewsclient.utils.App;
import com.example.mynewsclient.utils.SpUtils;
import com.suke.widget.SwitchButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    private String username;
    private String password;
    private SwitchButton switchButton;
    //    @Bind(R.id.link_signup) TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        switchButton = (SwitchButton) findViewById(R.id.switch_button);

        _emailText.setText(SpUtils.getString(getApplicationContext(), App.USERNAME));
        _passwordText.setText(SpUtils.getString(getApplicationContext(), App.PASSWORD));
        boolean saveUsername = SpUtils.getisBoolean_false(getApplicationContext(), App.SAVE_USERNAME_AND_PASSWORD, false);
        switchButton.setChecked(saveUsername);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                SpUtils.saveisBoolean(getApplicationContext(), App.SAVE_USERNAME_AND_PASSWORD, switchButton.isChecked());

            }
        });

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        /*_signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });*/
    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("验证中...");
        progressDialog.show();

        username = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (TextUtils.equals("admin", username)&&TextUtils.equals("admin", password)){
                            onLoginSuccess();

                        }else{
                            onLoginFailed();
                        }
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1500);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }
    /**
     * 用户名和密码判空和长度验证成功后
     */
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
//        finish();
        startActivity(new Intent(this,TabMainActivity.class));
        if (switchButton.isChecked()){
            SpUtils.saveString(getApplicationContext(), App.USERNAME,username);
            SpUtils.saveString(getApplicationContext(), App.PASSWORD,password);
        }else {
            SpUtils.saveString(getApplicationContext(), App.USERNAME,"");
            SpUtils.saveString(getApplicationContext(), App.PASSWORD,"");
        }
    }

    /**
     * 用户名和密码判空和长度验证失败后
     */
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);

        if (switchButton.isChecked()){
            SpUtils.saveString(getApplicationContext(), App.USERNAME,username);
            SpUtils.saveString(getApplicationContext(), App.PASSWORD,password);
        }else {
            SpUtils.saveString(getApplicationContext(), App.USERNAME,"");
            SpUtils.saveString(getApplicationContext(), App.PASSWORD,"");
        }
    }

    /**
     * 用户名和密码判空和长度验证
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()/* || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()*/) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
