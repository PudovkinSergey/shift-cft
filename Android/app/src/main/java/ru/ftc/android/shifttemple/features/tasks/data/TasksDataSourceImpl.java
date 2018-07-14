package ru.ftc.android.shifttemple.features.tasks.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

public final class TasksDataSourceImpl implements TasksDataSource {
    private final TasksApi tasksApi;

    public TasksDataSourceImpl(TasksApi tasksApi) {
        this.tasksApi = tasksApi;
    }

    @Override
    public void getTasks(final Carry<List<Task>> carry) {
        tasksApi.getTaskList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getTask(String id, Carry<Task> carry) {
        tasksApi.getTask(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createTask(Task task, Carry<Task> carry) {
        tasksApi.createTask(task).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteTask(String id, Carry<Success> carry) {
        tasksApi.deleteTask(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getTaskBids(String id, Carry<List<Bid>> carry) {
        tasksApi.getTaskBids(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void finishTask(String id, String status, Carry<Success> carry) {
        tasksApi.finishTask(id, status).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void chooseTaskBid(String id, Bid bid, Carry<Success> carry) {
        tasksApi.chooseTaskBid(id, bid).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createTaskBid(String id, Bid bid, Carry<Bid> carry) {
        tasksApi.createTaskBid(id, bid).enqueue(new DefaultCallback(carry));
    }
}
