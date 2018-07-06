package ru.ftc.android.shifttemple.features.users.presentation;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.users.domain.UsersInteractor;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;


final class UserLoginPresenter extends MvpPresenter<UserLoginView> {

    private String loginText;
    private String passwordText;
    private final UsersInteractor interactor;

    UserLoginPresenter(UsersInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        view.hideProgress();
    }

    public void onLoginTextChanged(final String text) {
        loginText = text;
    }

    public void onPasswordTextChanged(final String text) {
        passwordText = text;
    }


    public void onLoginButtonClicked() {
        if (loginText.isEmpty() || passwordText.isEmpty()) {
            view.showError("Fill all fields");
            return;
        }
        view.showProgress();
        interactor.loginUser(loginText, passwordText, new Carry<User>() {

            @Override
            public void onSuccess(User result) {
                // view hide etc
                view.showError("Hello " + result.getTitle());
                view.hideProgress();
                view.hideActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });

    }
}

