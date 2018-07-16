package ru.ftc.android.shifttemple.features.users.domain;

import ru.ftc.android.shifttemple.exception.UnknownException;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersRepository;

import ru.ftc.android.shifttemple.features.users.domain.model.User;

import ru.ftc.android.shifttemple.network.Carry;

public final class UsersInteractorImpl implements UsersInteractor {

    private final UsersRepository repository;
    private final UsersLocalRepository repositoryLocal;

    public UsersInteractorImpl(UsersRepository repository, UsersLocalRepository repositoryLocal) {
        this.repository = repository;
        this.repositoryLocal = repositoryLocal;
    }


    @Override
    public void loadUser(String id, Carry<User> carry) {
        repository.loadUser(id, carry);
    }

    @Override
    public void loginUser(String login, String password, final Carry<User> carry) {
        repository.loginUser(login, password, new Carry<User>() {
            @Override
            public void onSuccess(User result) {
                repositoryLocal.setUserToken(result.getToken());
                repositoryLocal.setUser(result);
                result.setToken("");
                carry.onSuccess(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                carry.onFailure(throwable);
            }
        });

    }

    @Override
    public void logoutUser() {
        repositoryLocal.setUserToken("");
        repositoryLocal.setUser(null);
    }

    @Override
    public void createUser(User user, Carry<User> carry) {
        repository.createUser(user, carry);
    }

    @Override
    public void checkUserToken(String token, Carry<Success> carry) {
        repository.checkUserToken(token, carry);
    }


    @Override
    public void loadLocalUser(Carry<User> carry) {
        final User user = repositoryLocal.getUser();
        if(user != null) {
            carry.onSuccess(user);
        } else {
            carry.onFailure(new UnknownException("Empty local user"));
        }
    }
}
