package ru.ftc.android.shifttemple.features.users.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.users.data.UsersApi;
import ru.ftc.android.shifttemple.features.users.data.UsersDataSource;
import ru.ftc.android.shifttemple.features.users.data.UsersDataSourceImpl;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalDataSource;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalDataSourceImpl;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersLocalRepositoryImpl;
import ru.ftc.android.shifttemple.features.users.data.UsersRepository;
import ru.ftc.android.shifttemple.features.users.data.UsersRepositoryImpl;

import ru.ftc.android.shifttemple.features.users.domain.UsersInteractor;
import ru.ftc.android.shifttemple.features.users.domain.UsersInteractorImpl;


final class PresenterFactory {


   private static UsersInteractor createUserInteractor(Context context) {
        final UsersApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(UsersApi.class);


        final UsersLocalDataSource dataSourceLocal = new UsersLocalDataSourceImpl(context);
        final UsersLocalRepository repositoryLocal = new UsersLocalRepositoryImpl(dataSourceLocal);


        final UsersDataSource dataSource = new UsersDataSourceImpl(api);
        final UsersRepository repository = new UsersRepositoryImpl(dataSource);
        return new UsersInteractorImpl(repository, repositoryLocal);


    }

   static UserLoginPresenter createUserLoginPresenter(Context context){
       return new UserLoginPresenter(createUserInteractor(context));
   }
   static UserProfilePresenter createUserProfilePresenter(Context context) {

        return new UserProfilePresenter(createUserInteractor(context));
    }

    static UserRegisterPresenter createUserRegisterPresenter(Context context){
       return new UserRegisterPresenter(createUserInteractor(context));
    }
}
