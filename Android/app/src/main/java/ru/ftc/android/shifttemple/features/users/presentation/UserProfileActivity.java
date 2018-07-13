package ru.ftc.android.shifttemple.features.users.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.presentation.NewTaskActivity;
import ru.ftc.android.shifttemple.features.tasks.presentation.TasksActivity;
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
    private FloatingActionButton createTaskButton;

    private Button logoutButton;

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
        createTaskButton=findViewById(R.id.create_task_button);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.onCreateTaskClicked();
            }
        });

        logoutButton = findViewById(R.id.logout_user_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogOutClicked();
            }
        });

    }

    @Override
    public void showNewTaskForm() {
        Intent intent = new Intent(UserProfileActivity.this, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void hideActivity() {
        finish();
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
