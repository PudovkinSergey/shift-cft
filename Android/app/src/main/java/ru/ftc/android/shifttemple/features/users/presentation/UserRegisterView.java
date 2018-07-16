package ru.ftc.android.shifttemple.features.users.presentation;


import ru.ftc.android.shifttemple.features.MvpView;

public interface UserRegisterView extends MvpView {
    void showProgress();

    void hideProgress();

    void showError(String message);

    void hideActivity();
}