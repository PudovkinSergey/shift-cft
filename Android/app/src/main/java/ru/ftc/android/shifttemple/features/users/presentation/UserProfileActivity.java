package ru.ftc.android.shifttemple.features.users.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.users.domain.model.User;

/**
 * Created by Pudov on 09.07.2018.
 */

public class UserProfileActivity extends BaseActivity implements UserProfileView {
    private ProgressBar progressBar;
    private TextView helloText;
    private TextView karmaText;
    private TextView phoneNumberText;
    private LinearLayout activity;

    UserProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        initView();
    }

    private void initView() {
        progressBar=findViewById(R.id.profile_progress);
        helloText=findViewById(R.id.hello_text);
        karmaText=findViewById(R.id.karma_text);
        phoneNumberText=findViewById(R.id.phone_number_text);
        activity=findViewById(R.id.activity_profile);


    }


    @Override
    public void showProgress() {
        activity.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        activity.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG);
    }

    @Override
    public void showProfile(User user) {
        helloText.setText(getString(R.string.hello_text,user.getName()));
        phoneNumberText.setText(getString(R.string.phone_number_text,user.getPhone()));
        karmaText.setText(getString(R.string.karma_text,String.valueOf(user.getKarma())));
    }


    @Override
    protected MvpPresenter<UserProfileView> getPresenter() {
        presenter= PresenterFactory.createUserProfilePresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

}
