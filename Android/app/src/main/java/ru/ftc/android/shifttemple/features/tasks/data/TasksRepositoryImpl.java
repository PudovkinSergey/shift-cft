package ru.ftc.android.shifttemple.features.tasks.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;

public final class TasksRepositoryImpl implements TasksRepository {

    private final TasksDataSource dataSource;

    public TasksRepositoryImpl(TasksDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadTasks(Carry<List<Task>> carry) {
        dataSource.getTasks(carry);
    }

    @Override
    public void loadTask(String id, Carry<Task> carry) {
        dataSource.getTask(id, carry);
    }

    @Override
    public void createTask(Task task, Carry<Task> carry) {
        dataSource.createTask(task, carry);
    }

    @Override
    public void deleteTask(String id, Carry<Success> carry) {
        dataSource.deleteTask(id, carry);
    }

    @Override
    public void loadTaskBids(String id, Carry<List<Bid>> carry) {
        dataSource.getTaskBids(id, carry);
    }

    @Override
    public void createTaskBid(String id, Bid bid, Carry<Bid> carry) {
        dataSource.createTaskBid(id, bid, carry);
    }

    @Override
    public void finishTask(String id, String status, Carry<Success> carry) {
        dataSource.finishTask(id, status, carry);
    }

    @Override
    public void chooseTaskBid(String id, Bid bid, Carry<Success> carry) {
        dataSource.chooseTaskBid(id, bid, carry);
    }
}
