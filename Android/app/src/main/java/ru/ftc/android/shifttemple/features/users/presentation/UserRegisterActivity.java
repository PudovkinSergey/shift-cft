package ru.ftc.android.shifttemple.features.users.presentation;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
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

public final class UserRegisterActivity extends BaseActivity implements UserLoginView {

    private ProgressBar progressBar;
    private EditText inputName;
    private EditText inputPhone;
    private EditText inputLogin;
    private EditText inputPassword;
    private Button registerButton;



    private UserRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.reg_progress);

        registerButton = findViewById(R.id.btn_reg);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegisterButtonClicked();
            }
        });


        inputName = findViewById(R.id.reg_input_name);
        inputPhone = findViewById(R.id.reg_input_phone);


        inputLogin = findViewById(R.id.reg_input_login);
        inputPassword = findViewById(R.id.reg_input_password);


        inputLogin.addTextChangedListener(loginWatcher);
        inputPassword.addTextChangedListener(passwordWatcher);


        inputPhone.addTextChangedListener(phoneWatcher);
        inputName.addTextChangedListener(nameWatcher);

        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

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


    private final TextWatcher nameWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            presenter.onNameTextChanged(s.toString());
        }
    };


    private final TextWatcher phoneWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            presenter.onPhoneTextChanged(s.toString());
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
        presenter = PresenterFactory.createUserRegisterPresenter(this);
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
