package ru.ftc.android.shifttemple.features.tasks.presentation;


import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;

interface TaskView extends MvpView {

    void showProgress();

    void hideProgress();

    void showBidList(List<Bid> list);

    void showTask(Task task);

    void showError(String message);

    void showLoginForm();

    void showConfirmationDialog(Bid bid);

    void showResponseSuccess();

    void showInputBidTextDialog();

    void changeCloseButtonVisibility(boolean hide);

    void leaveTaskActivity();
}
