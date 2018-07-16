package ru.ftc.android.shifttemple.features.users.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;


public final class UserLoginActivity extends BaseActivity implements UserLoginView {

    private ProgressBar progressBar;
    private EditText inputLogin;
    private EditText inputPassword;
    private Button loginButton;
    private Button linkRegisterButton;


    private UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.login_progress);

        loginButton = findViewById(R.id.btn_login);

        linkRegisterButton = findViewById(R.id.btn_link_reg);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonClicked();
            }
        });

        linkRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
                startActivity(intent);
            }
        });


        inputLogin = findViewById(R.id.login_input_login);
        inputPassword = findViewById(R.id.login_input_password);


        inputLogin.addTextChangedListener(loginWatcher);
        inputPassword.addTextChangedListener(passwordWatcher);

    }


    private final TextWatcher loginWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {
            presenter.onLoginTextChanged(s.toString());
        }
    };

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            presenter.onPasswordTextChanged(s.toString());
        }
    };


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        //recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        //recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<UserLoginView> getPresenter() {
        presenter = PresenterFactory.createUserLoginPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void hideActivity() {
        this.finish();
    }
}
