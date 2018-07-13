package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.tasks.data.BidsLocalDataSource;
import ru.ftc.android.shifttemple.features.tasks.data.BidsLocalDataSourceImpl;
import ru.ftc.android.shifttemple.features.tasks.data.TasksApi;
import ru.ftc.android.shifttemple.features.tasks.data.TasksDataSource;
import ru.ftc.android.shifttemple.features.tasks.data.TasksDataSourceImpl;
import ru.ftc.android.shifttemple.features.tasks.data.TasksRepository;
import ru.ftc.android.shifttemple.features.tasks.data.TasksRepositoryImpl;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractorImpl;
import ru.ftc.android.shifttemple.features.users.data.UserLocalRepositoryFactory;
import ru.ftc.android.shifttemple.features.users.data.UsersApi;
import ru.ftc.android.shifttemple.features.users.data.UsersDataSource;
import ru.ftc.android.shifttemple.features.users.data.UsersDataSourceImpl;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersRepositoryImpl;

final class PresenterFactory {

    private static TasksInteractor createTasksInteractor(Context context) {

        final TasksApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(TasksApi.class);

        final UsersApi apiUser = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(UsersApi.class);

        final UsersLocalRepository usersRepositoryLocal = UserLocalRepositoryFactory.create(context);

        final TasksDataSource dataSource = new TasksDataSourceImpl(api);
        final TasksRepository repository = new TasksRepositoryImpl(dataSource);

        final UsersDataSource dataSourceServer = new UsersDataSourceImpl(apiUser);
        final UsersRepository repositoryServer = new UsersRepositoryImpl(dataSourceServer);

        return new TasksInteractorImpl(repository, usersRepositoryLocal, repositoryServer );
    }

    static TasksListPresenter createTaskListPresenter(Context context) {
        return new TasksListPresenter(createTasksInteractor(context));
    }


    static TaskPresenter createTaskPresenter(Context context) {
        TasksInteractor tasksInteractor = createTasksInteractor(context);
        BidsLocalDataSource bidsLocalDataSource = new BidsLocalDataSourceImpl(context);
        return new TaskPresenter(tasksInteractor, bidsLocalDataSource);
    }


    static NewTaskPresenter createNewTaskPresenter(Context context) {
        return new NewTaskPresenter(createTasksInteractor(context));
    }


}
