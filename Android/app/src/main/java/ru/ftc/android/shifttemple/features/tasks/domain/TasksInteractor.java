package ru.ftc.android.shifttemple.features.tasks.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public interface TasksInteractor {

    void loadTasks(Carry<List<Task>> carry);

    void loadTask(String id, Carry<Task> carry);

    void createTask(Task task, Carry<Task> carry);

    void deleteTask(String id, Carry<Success> carry);


    void loadTaskBids(String id, Carry<List<Bid>> carry);

    void createTaskBid(String id, Bid bid, Carry<Bid> carry);

    void chooseTaskBid(String id, Bid bid, Carry<Success> carry);

    void finishTask(String id, String status,  Carry<Success> carry);


    //TODO is bad, but hz

    void loadLocalUser(Carry<User> carry);

    void loadUserFromServer(Carry<User> carry);


}
