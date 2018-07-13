package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.presentation.UserLoginLoginActivity;

public final class TaskActivity extends BaseActivity implements TaskView {

    private static final String TASK_ID = "taskId";

    private RecyclerView recyclerView;
    private TaskDetailAdapter adapter;
    private TaskPresenter presenter;
    private Button responceButton;
    private Button closeTaskButton;
    private String task_id;

    public static void start(Context context, final Task task) {
        Intent intent = new Intent(context, TaskActivity.class);

        Bundle b = new Bundle();
        b.putString(TASK_ID, task.getId());
        intent.putExtras(b);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);

        Bundle b = getIntent().getExtras();
        if (b != null)
            task_id = b.getString(TASK_ID);
        presenter.setTaskId(task_id);

        initView();

        //TODO test code
//        Task task = new Task("asda", "Titles dfd sf sdf sd fs", "asd d sa dsa j fghfjk dgh jkfdhgh jkfdhjkg fhdkjg hfdkjg hfdkjjg hfdjkg hfjkghdfjk h gjkfdhgjkfgh kjfdhgjk fdhgjk hdgjkdf hdsa ");
//        adapter.setTask(task);
//
//        ArrayList<Bid> bids = new ArrayList<>(10);
//
//        for(int i = 0; i < 10; i++) {
//            bids.add(new Bid("qweq", "asd sadas das dssdf ds fdsa"));
//        }
//
//        adapter.setBids(bids);
        //TODO test code
    }


    private void initView() {
        recyclerView = findViewById(R.id.dataView);

        adapter = new TaskDetailAdapter(this, new TaskDetailAdapter.SelectBidListener() {
            @Override
            public void onBidSelect(Bid bid) {
                presenter.onBidSelected(bid);
            }

            @Override
            public void onBidFinishTaskClicked(Bid bid) {
                ///TODO: presenter //
            }

            @Override
            public void onBidLongClick(Bid bid) {
                presenter.onBidLongClicked(bid);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        responceButton = findViewById(R.id.responseButton);
                responceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateBidClicked();
            }
        });

        closeTaskButton = findViewById(R.id.closeTaskButton);
        closeTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCloseTaskClicked();
            }
        });

    }

    @Override
    public void showProgress() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
//        mSwipeRefreshLayout.setRefreshing(false);
//        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBidList(List<Bid> list) {
        adapter.setBids(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<TaskView> getPresenter() {
        presenter = PresenterFactory.createTaskPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showLoginForm() {
        Intent intent = new Intent(TaskActivity.this, UserLoginLoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTask(Task task) {
        adapter.setTask(task);
        if (task.getTaskIsMine()) {
            responceButton.setVisibility(View.GONE);
        } else {
            responceButton.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showInputBidTextDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        builder.setTitle("Create Bid Answer")
                .setView(input)
                .setMessage("Input your answer please:")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.onBidTextEntered(input.getText().toString());
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void changeCloseButtonVisibility(boolean hide) {
        if (hide) {
            closeTaskButton.setVisibility(View.GONE);
        } else {
            closeTaskButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void leaveTaskActivity() {
        onBackPressed();
    }

    @Override
    public void showConfirmationDialog(final Bid bid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose bid")
                .setMessage("Are you sure you want to choose this bid?\n" + bid.getText())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.onBidChoosed(bid);
                    }
                })
                .setNegativeButton(android.R.string.no, null) //TODO use other string res
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public void showResponseSuccess() {
        showError(getString(R.string.respond_toast));
    }
}
