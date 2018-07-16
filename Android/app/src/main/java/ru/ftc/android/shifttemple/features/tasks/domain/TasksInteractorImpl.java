package ru.ftc.android.shifttemple.features.tasks.domain;

import java.util.List;

import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.exception.UnknownException;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.data.TasksRepository;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersRepository;
import ru.ftc.android.shifttemple.features.users.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public final class TasksInteractorImpl implements TasksInteractor {

    private final TasksRepository repository;

    private final UsersLocalRepository repositoryUsersLocal;
    private UsersRepository repositoryUsersServer;

    public TasksInteractorImpl(TasksRepository repository, UsersLocalRepository repositoryUsersLocal, UsersRepository repositoryUsersServer) {
        this.repository = repository;
        this.repositoryUsersLocal = repositoryUsersLocal;
        this.repositoryUsersServer = repositoryUsersServer;
    }


    private Boolean checkUserToken(final Carry carry) {
        if (repositoryUsersLocal.getUserToken().isEmpty()) {
            carry.onFailure(new NotAuthorizedException());
            return false;
        }
        return true;
    }


    private Boolean checkTaskIsMine(Task task) {
        Boolean result = false;

        User user = repositoryUsersLocal.getUser();
        if (user != null && task.getUserId() != null) {
            result = (task.getUserId().equals(user.getId()));
        }

        return result;
    }

    @Override
    public void chooseTaskBid(String id, Bid bid, Carry<Success> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.chooseTaskBid(id, bid, carry);
    }

    @Override
    public void finishTask(String id, String status, Carry<Success> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.finishTask(id, status, carry);
    }

    @Override
    public void loadTasks(Carry<List<Task>> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.loadTasks(carry);
    }

    @Override
    public void loadTask(String id, final Carry<Task> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        //TODO: ask it's normal?
        repository.loadTask(id, new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                result.setTaskIsMine(checkTaskIsMine(result));
                carry.onSuccess(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                carry.onFailure(throwable);
            }
        });

    }

    @Override
    public void createTask(Task task, Carry<Task> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.createTask(task, carry);
    }

    @Override
    public void deleteTask(String id, Carry<Success> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.deleteTask(id, carry);
    }

    @Override
    public void createTaskBid(String id, Bid bid, Carry<Bid> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.createTaskBid(id, bid, carry);
    }

    @Override
    public void loadTaskBids(String id, Carry<List<Bid>> carry) {
        if (!checkUserToken(carry)) {
            return;
        }
        repository.loadTaskBids(id, carry);
    }

    @Override
    public void loadLocalUser(Carry<User> carry) {
        final User user = repositoryUsersLocal.getUser();
        if (user != null) {
            carry.onSuccess(user);
        } else {
            carry.onFailure(new UnknownException("Empty local user"));
        }
    }

    @Override
    public void loadUserFromServer(Carry<User> carry) {
        if (!checkUserToken(carry)) {
            return;
        }

        User user = repositoryUsersLocal.getUser();
        carry.onSuccess(user);

        if (user != null) {
            repositoryUsersServer.loadUser(user.getId(), new Carry<User>() {
                @Override
                public void onSuccess(User result) {
                    repositoryUsersLocal.setUser(result);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    //
                }

            });
        }



    }
}
