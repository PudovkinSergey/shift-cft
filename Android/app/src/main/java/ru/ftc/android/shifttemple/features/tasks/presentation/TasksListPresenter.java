package ru.ftc.android.shifttemple.features.tasks.presentation;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

final class TasksListPresenter extends MvpPresenter<TasksListView> {
    private final TasksInteractor interactor;
    private User user;

    TasksListPresenter(TasksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadUserInfo();
        loadTasks();
    }

    private void loadUserInfo() {
        view.showProgress();
        interactor.loadUserFromServer(new Carry<User>() {
            @Override
            public void onSuccess(User result) {
                user = result;
                view.showUserInfo(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private void loadTasks() {
        view.showProgress();
        interactor.loadTasks(new Carry<List<Task>>() {

            @Override
            public void onSuccess(List<Task> result) {
                if (view == null) {
                    return;
                }

                ArrayList<Task> listTasks = new ArrayList<Task>();
                for (Task task : result) {
                    if (task.getStatus() == true) {
                        listTasks.add(task);
                    }
                }

                view.showTaskList(listTasks);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());

                if (throwable instanceof NotAuthorizedException) {
                    view.showLoginForm();
                }

            }

        });
    }

    void onRefreshTasks() {
        loadTasks();

    }

    void onTaskSelected(Task task) {
        view.showProgress();
        view.showTask(task);
        view.hideProgress();
    }

    void onTaskLongClicked(Task task) {
        view.showError("May be added to favorite.. May be no;)"); // TODO: favorite
    }


    public void onCreateTaskClicked() {
        if (user.getKarma()>0){
            view.showNewTaskForm();
        } else {
            view.showError("You do not have enough karma, help someone");
        }

    }
}
