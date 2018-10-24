package vlab.android.architecture.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vlab.android.architecture.feature.validator.TextValidator;

/**
 * Created by Vinh Tran on 10/24/18.
 */
@Module
public class UseCaseModule {

    @Singleton
    @Provides
    TextValidator provideTextValidator(){
        return new TextValidator();
    }
}
