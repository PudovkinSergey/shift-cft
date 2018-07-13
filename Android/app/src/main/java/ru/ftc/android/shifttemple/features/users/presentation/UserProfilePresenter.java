package ru.ftc.android.shifttemple.features.users.presentation;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.users.domain.UsersInteractor;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

/**
 *  Created by Pudov on 09.07.2018.
 */

public class UserProfilePresenter extends MvpPresenter<UserProfileView> {
    private final UsersInteractor interactor;

    public UserProfilePresenter(UsersInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadProfile();
        view.hideProgress();
    }

    private void loadProfile() {
        view.showProgress();


        interactor.loadLocalUser(new Carry<User>(){
            @Override
            public void onSuccess(User result) {

                view.showProfile(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        }
        );

    }
    public void onCreateTaskClicked() {
        view.showNewTaskForm();

    }
}
