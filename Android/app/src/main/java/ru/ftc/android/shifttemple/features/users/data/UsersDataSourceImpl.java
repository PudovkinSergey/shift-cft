package ru.ftc.android.shifttemple.features.users.data;


import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

public final class UsersDataSourceImpl implements UsersDataSource {
    private final UsersApi usersApi;

    public UsersDataSourceImpl(UsersApi usersApi) {
        this.usersApi = usersApi;
    }



    @Override
    public void getUser(String id, Carry<User> carry) {
        usersApi.getUser(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createUser(User user, Carry<User> carry) {
        usersApi.createUser(user).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void loginUser(String login, String password, Carry<User> carry) {
        usersApi.loginUser(login, password).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void checkUserToken(String token, Carry<Success> carry) {
        usersApi.checkUserToken(token).enqueue(new DefaultCallback(carry));
    }
}
