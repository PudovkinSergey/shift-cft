package ru.ftc.android.shifttemple.features.tasks.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;

final class NewTaskPresenter extends MvpPresenter<NewTaskView> {
    private final TasksInteractor interactor;

    private String titleText = "";
    private String descriptionText = "";
    private String shortDescText = "";


    NewTaskPresenter(TasksInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        view.hideProgress();
        view.showError("New task");
    }


    public void onCreateButtonClicked() {
        if(titleText.isEmpty() || descriptionText.isEmpty()){
            view.showError("Fill all fields");
            return;
        }
        view.showProgress();

        view.showError("Your input:\n" + titleText + "\n" + descriptionText);

        final Task task = new Task(titleText, shortDescText, descriptionText);
        interactor.createTask(task, new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                view.hideActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());

            }
        });
    }

    public void onTitleTextChanged(final String s) {
        titleText = s;
    }

    public void onDescriptionTextChanged(final String s) {
        descriptionText = s;
    }

    public void onShortDescriptionTextChanged(String s) {
        shortDescText = s;
    }
}
