package ru.ftc.android.shifttemple.features.users.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.users.domain.model.User;

/**
 *  Created by Pudov on 09.07.2018.
 */

public interface UserProfileView extends MvpView {
    void showProgress();
    void hideProgress();
    void showError(String message);
    void showProfile(User user);
    void showNewTaskForm();
}
