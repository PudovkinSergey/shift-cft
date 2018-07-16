package ru.ftc.android.shifttemple.features.users.data;


import ru.ftc.android.shifttemple.features.books.domain.model.Success;

import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public final class UsersRepositoryImpl implements UsersRepository {

    private final UsersDataSource dataSource;

    public UsersRepositoryImpl(UsersDataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public void loadUser(String id, Carry<User> carry) {
        dataSource.getUser(id, carry);
    }

    @Override
    public void createUser(User user, Carry<User> carry) {
        dataSource.createUser(user, carry);
    }

    @Override
    public void checkUserToken(String token, Carry<Success> carry) {
        dataSource.checkUserToken(token, carry);
    }

    @Override
    public void loginUser(String login, String password, Carry<User> carry) {
        dataSource.loginUser(login, password, carry);
    }
}
