package ru.ftc.android.shifttemple.features.users.presentation;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.users.domain.UsersInteractor;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

final class UserRegisterPresenter extends MvpPresenter<UserLoginView> {

    private String loginText = "";
    private String passwordText = "";
    private String nameText = "";
    private String phoneText = "";

    private final UsersInteractor interactor;

    UserRegisterPresenter(UsersInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        interactor.logoutUser();
        view.hideProgress();
    }

    public void onLoginTextChanged(final String text) {
        loginText = text;
    }

    public void onPasswordTextChanged(final String text) {
        passwordText = text;
    }


    public void onRegisterButtonClicked() {
        if (loginText.isEmpty() || passwordText.isEmpty()
                || nameText.isEmpty() || phoneText.isEmpty()) {
            view.showError("Fill all fields");
            return;
        }
        view.showProgress();
        User user = new User("", nameText, phoneText);

        interactor.createUser(loginText, passwordText, user, new Carry<User>() {
            @Override
            public void onSuccess(User result) {
                view.hideProgress();
                view.showError("Complete! Now login, " + result.getName());
                view.hideActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });

    }

    public void onNameTextChanged(final String s) {
        nameText = s;
    }

    public void onPhoneTextChanged(final String s) {
        phoneText = s;
    }
}