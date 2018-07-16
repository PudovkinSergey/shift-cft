package ru.ftc.android.shifttemple.features.users.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public interface UsersInteractor {

    void loadUser(String id, Carry<User> carry);

    void createUser(User user, Carry<User> carry);

    void checkUserToken(String token, Carry<Success> carry);

    void loginUser(String login, String password, Carry<User> carry);

    void logoutUser();

    void loadLocalUser(Carry<User> carry);
}
